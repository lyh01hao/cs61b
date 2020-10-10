import java.awt.Color;


import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;

    public SeamCarver(Picture picture) {
        this.picture = picture;
        this.height = picture.height();
        this.width = picture.width();
    }
    public Picture picture() {                      // current picture
        return new Picture(this.picture);
    }
    public     int width() {                       // width of current picture
        return width;
    }
    public     int height() {                       // height of current picture
        return this.height;
    }

    private double calculateEnergyOfX(int col, int row) {
        int left = (col - 1 + width) % width;
        int right = (col + 1 + width) % width;

        Color leftPixel = picture.get(left, row);
        Color rightPixel = picture.get(right, row);

        double rx = leftPixel.getRed() - rightPixel.getRed();
        double gx = leftPixel.getGreen() - rightPixel.getGreen();
        double bx = leftPixel.getBlue() - rightPixel.getBlue();

        return (rx * rx) + (gx * gx) + (bx * bx);
    }

    private double calculateEnergyOfY(int col, int row) {
        int up = (row - 1 + height) % height;
        int down = (row + 1 + height) % height;

        Color upPixel = picture.get(col, up);
        Color downPixel = picture.get(col, down);

        double ry = upPixel.getRed() - downPixel.getRed();
        double gy = upPixel.getGreen() - downPixel.getGreen();
        double by = upPixel.getBlue() - downPixel.getBlue();

        return (ry * ry) + (gy * gy) + (by * by);
    }

    public  double energy(int x, int y) {           // energy of pixel at column x and row y
        if (x >= width || x < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (y >= height || y < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        double energy = calculateEnergyOfX(x, y) + calculateEnergyOfY(x, y);
        return energy;
    }
    public   int[] findHorizontalSeam() {           // sequence of indices for horizontal seam
        transpose();
        int[] seam = findVerticalSeam();
        transpose();
        return seam;
    }
    private void transpose() {
        Picture temp = new Picture(height, width);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                temp.set(row, col, picture.get(col, row));
            }
        }

        picture = temp;
        int t = width;
        width = height;
        height = t;
    }
    public   int[] findVerticalSeam() {             // sequence of indices for vertical seam
//        double[][] minimumCost = new double[width][height];
//        int[][] edgeTo = new int[width][height];
//        for (int i = 0; i < width; i++) {
//            minimumCost[i][0] = energy(i, 0);
//        }
//        for (int i = 0; i < width; i++) {
//            for (int j = 1; j < height; j++) {
//                int flag;
//                double smaller = 0;
//                if (i == 0) {
//                    if (minimumCost[i][j - 1] < minimumCost[i + 1][j - 1]) {
//                        flag = 0;
//                        smaller = minimumCost[i][j - 1];
//                    } else {
//                        flag = 1;
//                        smaller = minimumCost[i + 1][j - 1];
//                    }
//                } else if (i == width - 1) {
//                    if (minimumCost[i][j - 1] < minimumCost[i - 1][j - 1]) {
//                        flag = 0;
//                        smaller = minimumCost[i][j - 1];
//                    } else {
//                        flag = -1;
//                        smaller = minimumCost[i - 1][j - 1];
//                    }
//                } else {
//                    if (minimumCost[i][j - 1] < minimumCost[i - 1][j - 1] && minimumCost[i][j - 1] < minimumCost[i + 1][j - 1]) {
//                        flag = 0;
//                        smaller = minimumCost[i][j - 1];
//                    } else if (minimumCost[i - 1][j - 1] < minimumCost[i + 1][j - 1]) {
//                        flag = -1;
//                        smaller = minimumCost[i - 1][j - 1];
//                    } else {
//                        flag = 1;
//                        smaller = minimumCost[i + 1][j - 1];
//                    }
//                }
//                edgeTo[i][j] = flag;
//                minimumCost[i][j] = energy(i, j) + smaller;
//            }
//        }
//        LinkedList<Integer> result = new LinkedList<>();
//        double min = Double.MAX_VALUE;
//        int mark = 0;
//        for (int i = 0; i < width; i++) {
//            if (minimumCost[i][height - 1] < min) {
//                mark = i;
//                min = minimumCost[i][height - 1];
//            }
//        }
//        result.add(mark);
//        int lastOne = mark;
//        for (int i = height - 1; i > 0; i--) {
//
//            result.add(edgeTo[lastOne][i] + lastOne);
//            lastOne = edgeTo[lastOne][i] + lastOne;
//        }
//        int[] trueResult = new int[result.size()];
//        for (int i = 0; i < result.size(); i++) {
//            trueResult[i] = result.get(i);
//        }
//        return trueResult;
        int[] seam = new int[height];
        double totalEnergy = Double.MAX_VALUE;

        for (int col = 0; col < width; col++) {
            int y = 0;
            int x = col;
            int[] temp = new int[height];
            double tempEnergy = energy(x, y);
            temp[y] = x;
            y++;

            double topE = 0.0, leftE = 0.0, rightE = 0.0;


            while (y < height) {
                int top = x;
                int left = x - 1;
                int right = x + 1;

                topE = energy(top, y);
                if (left >= 0) {
                    leftE = energy(left, y);
                } else {
                    leftE = Double.MAX_VALUE;
                }

                if (right < width) {
                    rightE = energy(right, y);
                } else {
                    rightE = Double.MAX_VALUE;
                }

                if (topE <= leftE && topE <= rightE) {
                    tempEnergy += topE;
                    temp[y] = top;
                    x = top;
                } else if (leftE <= topE && leftE <= rightE) {
                    tempEnergy += leftE;
                    temp[y] = left;
                    x = left;
                } else {
                    tempEnergy += rightE;
                    temp[y] = right;
                    x = right;
                }

                y++;
            }

            if (tempEnergy <= totalEnergy) {
                totalEnergy = tempEnergy;
                seam = temp;
            }
        }

        return seam;
    }
    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
        if (checkSeam(seam)) {
            this.picture = new Picture(SeamRemover.removeHorizontalSeam(this.picture, seam));
            height--;
        } else {
            throw new IllegalArgumentException();
        }
    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
        if (checkSeam(seam)) {
            this.picture = new Picture(SeamRemover.removeVerticalSeam(this.picture, seam));
            width--;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean checkSeam(int[] seam) {
        for (int i = 0; i < seam.length - 1; i++) {
            if (Math.abs(seam[i] - seam[i + 1]) > 1) {
                return false;
            }
        }

        return true;
    }
}

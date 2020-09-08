public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> charDeque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            charDeque.addLast(word.charAt(i));
        }
        return charDeque;
    }

    public boolean isPalindrome(String word) {
//        if (word.length() <= 1) {
//            return true;
//        }
//        Deque<Character> testWord = wordToDeque(word);
//        while (!testWord.isEmpty()){
//            if (testWord.size() == 1){
//                return true;
//            }
//            if (!testWord.removeFirst().equals(testWord.removeLast())) {
//                return false;
//            }
//        }
//        return true;
        Deque<Character> testWord = wordToDeque(word);
        return isPalindrome_re(testWord);
    }

    private boolean isPalindrome_re(Deque<Character> wo) {
        if (wo.size() <= 1) {
            return true;
        }
        if (!wo.removeLast().equals(wo.removeFirst())) {
            return false;
        }
        return isPalindrome_re(wo);
    }

    /*
    OffByOne
     */

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> testWord = wordToDeque(word);
        return isPalindrome_re(testWord, cc);
    }

    private boolean isPalindrome_re(Deque<Character> wo, CharacterComparator cc) {
        if (wo.size() <= 1) {
            return true;
        }
        if (!cc.equalChars(wo.removeLast(), wo.removeFirst())) {
            return false;
        }
        return isPalindrome_re(wo, cc);
    }
}

public class InitialNormalize {
    private String operandOne;
    private int exponentOne;
    private String operandTwo;
    private int exponentTwo;
    private boolean grs;
    private int digits;

    public InitialNormalize(String operandOne, int exponentOne, String operandTwo, int exponentTwo, boolean grs, int digits) {
        this.operandOne  = operandOne;
        this.exponentOne = exponentOne;
        this.operandTwo  = operandTwo;
        this.exponentTwo = exponentTwo;
        this.grs         = grs;
        this.digits      = digits;
    }

    public void shift() {
        if (this.exponentOne > this.exponentTwo) {
            char[] normalizedOperand = new char[this.operandTwo.length() + (this.exponentOne - this.exponentTwo)];
            int z = 0;

            for (int x = 0; x < this.operandTwo.length() + (this.exponentOne - this.exponentTwo); x++) {
                if (x != 1) { normalizedOperand[x] = '0'; } 
                else { normalizedOperand[x] = '.'; }
            }

            for (int y = (this.exponentOne - this.exponentTwo) + 1; y < this.operandTwo.length() + (this.exponentOne - this.exponentTwo); y++) {
                if (this.operandTwo.charAt(z) != '.') { normalizedOperand[y] = this.operandTwo.charAt(z); }
                else { y--; }
                z++;
            }

            this.operandTwo = String.valueOf(normalizedOperand);
        } else if (this.exponentOne < this.exponentTwo) {
            char[] normalizedOperand = new char[this.operandOne.length() + (this.exponentTwo - this.exponentOne)];
            int z = 0;

            for (int x = 0; x < this.operandOne.length() + (this.exponentTwo - this.exponentOne); x++) {
                if (x != 1) { normalizedOperand[x] = '0'; } 
                else { normalizedOperand[x] = '.'; }
            }

            for (int y = (this.exponentTwo - this.exponentOne) + 1; y < this.operandOne.length() + (this.exponentTwo - this.exponentOne); y++) {
                if (this.operandOne.charAt(z) != '.') { normalizedOperand[y] = this.operandOne.charAt(z); } 
                else { y--; }
                z++;
            }

            this.operandOne = String.valueOf(normalizedOperand);
        }
    }

    public void round() {
        if (!this.grs) {
            char[] roundedOperandOne = new char[this.digits + 1];
            char[] roundedOperandTwo = new char[this.digits + 1];
            int x = 0;

            for (x = 0; x < digits + 1; x++) {
                roundedOperandOne[x] = this.operandOne.charAt(x);
                roundedOperandTwo[x] = this.operandTwo.charAt(x);
            }

            if (this.operandOne.charAt(x) != '\0') { if (this.operandOne.charAt(x) == '1') { roundedOperandOne = roundUp(roundedOperandOne); } }

            if (this.operandTwo.charAt(x) != '\0') { if (this.operandTwo.charAt(x) == '1') { roundedOperandTwo = roundUp(roundedOperandTwo); } }

            this.operandOne = String.valueOf(roundedOperandOne);
            this.operandTwo = String.valueOf(roundedOperandTwo);
        } else {
        }
    }

    private char[] roundUp(char[] roundedOperand) {
        boolean overflow = true;

        for (int x = this.digits; x > 0 && overflow; x--) {
            if (roundedOperand[x] == '1' && overflow) { roundedOperand[x] = '0'; } 
            else if (roundedOperand[x] == '0' && overflow){
                roundedOperand[x] = '1';
                overflow = false;
            }
        }

        return roundedOperand;
    }

    public String getFirstOperand() { return this.operandOne; }

    public String getSecondOperand() { return this.operandTwo; }

    public static void main(String[] args) {
        InitialNormalize initialNormalize = new InitialNormalize("1.00111101", 5, "1.00111101", 3, false, 5);

        initialNormalize.shift();
        System.out.println(initialNormalize.getFirstOperand());
        System.out.println(initialNormalize.getSecondOperand());

        initialNormalize.round();
        System.out.println(initialNormalize.getFirstOperand());
        System.out.println(initialNormalize.getSecondOperand());
    }
}

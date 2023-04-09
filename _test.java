public class _test {
    public static void main(String[] args){
        String operandOne = "1.000";    int exponentOne = -1;
        String operandTwo = "-1.110";   int exponentTwo = -2;
        boolean grs = false;            int digits = 4;

        /* Use @Samuel Uy's Normalization here if operands are crazy like 1000.1111 and not in 1.f */

        InitialNormalize initialNormalize = new InitialNormalize(operandOne, exponentOne, operandTwo, exponentTwo, grs, digits);
        initialNormalize.performShift();
        initialNormalize.performRound();
        initialNormalize.performNegative();

        System.out.println("  " + initialNormalize.getFirstOperand() + " x2^" + initialNormalize.getExponent());
        System.out.println(" " + initialNormalize.getSecondOperand() + " x2^" + initialNormalize.getExponent());

        Operation operation = new Operation(initialNormalize.getFirstOperand(), initialNormalize.getSecondOperand(), grs, digits);
        operation.performAddition();
        System.out.println("-------------");
        System.out.println(" " + operation.getSum() + " x2^" + initialNormalize.getExponent());

        PostNormalize postNormalize = new PostNormalize(operation.getSum(), initialNormalize.getExponent(), digits);
        postNormalize.performShift();
        postNormalize.performRound();
        System.out.println("-------------");
        System.out.println("  " + postNormalize.getSum() + " x2^" + postNormalize.getExponent());

        /* Use @Samuel Uy's Normalization here if sum is 0.0001 and not in 10.f */
    }
}

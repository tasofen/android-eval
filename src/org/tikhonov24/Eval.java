/**
 * @autor Tikhonov Alexander
 * @version 0.1.0b
 * @link https://github.com/tikhonov24/android-eval
 */
package org.tikhonov24;

public class Eval
{
    public double exec(String str)
    {
        str = str.replaceAll("[^0-9*/+-.()E]", "");

        while(str.indexOf("(") >= 0)
        {
            int sim = 0;
            int startPosition = str.indexOf("(");
            int endPosition = 0;
            char c;

            for(int i=0; i<str.length(); ++i)
            {
                c = str.charAt(i);

                if(c == '(')
                {
                    if(sim == 0)
                    {
                        startPosition = i;
                    }
                    sim++;
                }
                else if(c == ')')
                {
                    sim--;
                    if(sim == 0)
                    {
                        endPosition = i;
                    }
                }

                if(startPosition < endPosition)
                {
                    String sub = str.substring(startPosition+1, endPosition);
                    String left = str.substring(0, startPosition);
                    String right = str.substring(endPosition + 1);
                    String result = Double.toString( exec(sub) );

                    str = left + result + right;

                    startPosition = 0;
                    endPosition = 0;
                }
            }

        }

        String actions[] = {"+", "-", "*", "/"};

        for(int i=0; i<actions.length; ++i)
        {
            int position = str.indexOf(actions[i]);

            if(position >= 0)
            {
                String leftStr = str.substring(0, position);
                String rightStr = str.substring(position+1);
                String action = str.substring(position, position+1);
                double leftResult = exec(leftStr);
                double rightResult = exec(rightStr);

                switch (action)
                {
                    case "+":
                        return leftResult + rightResult;
                    case "-":
                        return leftResult - rightResult;
                    case "*":
                        return leftResult * rightResult;
                    case "/":
                        return  leftResult / rightResult;
                }
            }
        }

        return Double.parseDouble(str);
    }
}

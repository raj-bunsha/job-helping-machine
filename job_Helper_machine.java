import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
public class job_Helper_machine
{
    // Counts of elements on each side
    private Map<String, Integer> left;
    private Map<String, Integer> right;
    public void parse(String eqn)//to split the equation in 2 parts
    {
        
        String[] sides = eqn.split("->");
        if(sides.length != 2) {
            throw new RuntimeException("Check your equation. There should be exactly one -> symbol somewhere");
        }
        parseSide(sides[0], this.left);
        parseSide(sides[1], this.right);
    }
    
     public boolean isBalanced()
    {
        return left.equals(right);//to check atoms are same or not
    }
    
    public boolean isSimpleBalanced()
    {
        return leftCount() == rightCount();//to check whether total no. of atoms are same or not
    }
    
    public int leftCount()
    {
        return left.values().stream().mapToInt(Integer::intValue).sum();//to count atoms on left hand side
    }
    
    public int rightCount()
    {
        return right.values().stream().mapToInt(Integer::intValue).sum();//to count atoms on right hand side
    }
    
     public job_Helper_machine(String eqn)//constructor to store collections
    {
        this.left = new HashMap<>();
        this.right = new HashMap<>();
        parse(eqn);
    }
    
    private void parseSide(String side, Map<String, Integer> counter)//to add elements in side hashmap
    {
        String[] molecules = side.split("\\+");
        for(String molecule : molecules) {
            parseMolecule(molecule, counter);
        }
    }
    
    private void parseMolecule(String molecule, Map<String, Integer> counter)
    {
        molecule = molecule.trim();
        Matcher matcher = Pattern.compile("([a-zA-Z]+)\\s*([0-9]*)").matcher(molecule);
        // matcher to extract elements from compounds
        int multiplier = 1;
        int endIndex = 0;
        while(matcher.find()) {
            String separator = molecule.substring(endIndex, matcher.start()).trim();
            if(!separator.isEmpty()) {
                // Check if there is a premultiplier before the first element
                if(endIndex == 0) {
                    String multiplierString = molecule.substring(0, matcher.start()).trim();
                    try {
                        multiplier = Integer.parseInt(multiplierString);
                    } catch(NumberFormatException nfe) {
                        throw new RuntimeException("Invalid prefix \"" + multiplierString +
                                                   "\" to molecule \"" + molecule.substring(matcher.start()) + "\"");
                    }
                } else {
                    throw new RuntimeException("Input valid characters Nonsensical characters \"" + separator +
                                               "\" in molecule \"" + molecule + "\"");//to throw exception in case of wrong characters
                }
            }
            parseElement(multiplier, matcher.group(1), matcher.group(2), counter);
            endIndex = matcher.end();
        }
        if(endIndex != molecule.length()) {
            throw new RuntimeException("Invalid end to side: \"" + molecule.substring(endIndex) + "\"");
           //to throw exception when the equation is not according to the format
       }
    }
    
    private void parseElement(int multiplier, String element, String atoms, Map<String, Integer> counter)
    {
        if(!atoms.isEmpty())
            multiplier *= Integer.parseInt(atoms);
        if(counter.containsKey(element))
            multiplier += counter.get(element);
        counter.put(element, multiplier);
    }
    
    public static void main()throws IOException,InterruptedException
    {
        boolean sdf=true;
        while(sdf==true){
     System.out.println("**        ** *******  **       *******  ********  ****    ****  *******");
     System.out.println("**   **   ** **       **       **       **    **  ** **  ** **  **     ");
     System.out.println("**  ****  ** ******   **       **       **    **  **  ****  **  ****** ");
     System.out.println(" ****  ****  **       **       **       **    **  **        **  **     ");
     System.out.println("  **    **   *******  *******  *******  ********  **        **  *******");
     Scanner in=new Scanner(System.in);
     InputStreamReader read=new InputStreamReader(System.in); 
     BufferedReader inty=new BufferedReader(read);
     System.out.println("Enter 1 if you are a chemist and want to  check whether chemical equation is balanced or not");
     System.out.println("Enter 2 if you are a physicist and you want to find the gravity on any planet");
     System.out.println("Enter 3 if you are a mathematician and want to calculate mathematical application");
     System.out.println("Enter 4 if you want to find the number of days you lived");
     System.out.println("Enter 5 if you want to find angle between hour and minute hands ");
     System.out.println("Enter 6 if you want to find the day of date");
     System.out.println("Enter 7 if you want to exit");
     double gravity=0;String jkl="",sim="";
     int opt=Integer.parseInt(inty.readLine());// to input the function
      System.out.print("Processing");
        for(int i=1;i<=3;i++)
        {
        System.out.print(".");
        Thread.sleep(1000);
       }
       System.out.println();
       if(opt==1)
        {
           System.out.println("Enter an equation:");
            String args[]=new String[1];
            args[0]=in.nextLine();
            // Collect all command line arguments into one equation
           String s="",formula;
           formula=args[0];
           ArrayList<String> elements = new ArrayList<>();
           args[0]="";
           for (int i=0; i<formula.length(); i++)
           {
              if (Character.isUpperCase(formula.charAt(i))) 
               
              {
              if(!s.isEmpty())
              {
                 elements.add(s);
              }
              s = "" + formula.charAt(i);
              } 
              else 
              {
                 s+=formula.charAt(i);
              }
           }
           elements.add(s);
           //line 135 to 152 create a sense of equation which can be parse the equation easily
           String element[][]=new String[elements.size()][];
           int i;
           for (i=0; i<elements.size(); i++) {
           args[0]+=elements.get(i) + " ";
           }
           i=1;
           System.out.println(args[0]);
           StringBuilder sb = new StringBuilder();
           for(String arg : args)
            sb.append(arg).append(' ');
            String eqn = sb.toString();
           job_Helper_machine parser =new job_Helper_machine(eqn);
           //to call all the function and constructor used above and store it in different variables
           
           boolean simpleBalanced = parser.isSimpleBalanced();
           boolean balanced = parser.isBalanced();
           int z=0;
            System.out.println("Left side atoms:- " + parser.leftCount());
           for(Map.Entry<String, Integer> entry : parser.left.entrySet()) {
            System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            z++;
             }
           //to show the elements and atoms on rhs
           String a[]=new String[z+1];
           int k=0;
           for(Map.Entry<String, Integer> entry : parser.left.entrySet()) {
            a[k]=entry.getKey();
            k++;
           }
           
           int l=0;
           int c=0,se=0;
           System.out.println();
           System.out.println("Right side atoms:- " + parser.rightCount());
           
            for(Map.Entry<String, Integer> entry : parser.right.entrySet()) {
            System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            s=entry.getKey();
             for(i=0;i<=z;i++) {
           
               if(s.equals(a[i]))
               {
                 l++;             
                 break;
               }
            
           }
           se++;
           }
           //to show the elements and atoms on rhs
           
           System.out.println();
           String foc="";
           boolean cv=false;
           if(k==l)
           {
               cv=true;
            }
            if(l>k)
            {
                foc="NEW ELEMENTS ARE INTRODUCED";
            }
            if(l<k)
            {
                foc="SOME ELEMENTS ARE DESTROYED";
            }
           System.out.println();
            int j=0;
            String d[]=new String[z+1];
            int e[]=new int[z+1];
            String f[]=new String[z+1];
            int g[]=new int[z+1];
            for(i=1;i<2;i++)
            {
              System.out.println("=============================================================================");
            }
            if(cv==true)
            {
              System.out.println("THE ELEMENTS ARE SAME"); 
            }   
             k=0;
           for(Map.Entry<String, Integer> entry : parser.left.entrySet()) {
                  d[k]=entry.getKey();
                  e[k]=entry.getValue();
                  k++;
           }
           k=0;
           for(Map.Entry<String, Integer> entry : parser.right.entrySet()) {
                  f[k]=entry.getKey();
                  g[k]=entry.getValue();
                  k++;
           }
            if(cv==false)
            {
              System.out.println(foc); 
            }
            i=0; 
            for(i=1;i<2;i++)
            {
              System.out.println("=============================================================================");
            }
            
             if(simpleBalanced==true&&balanced==true)
            {
              System.out.println("THE EQUATION IS BALANCED I.E. ELEMENTS ON BOTH SIDES ALONG WITH THEIR RESPECTIVE ATOMS ARE SAME ");
              for(i=1;i<2;i++)
              {
              System.out.println("=============================================================================");
              }
              System.out.println("DO YOU WANT TO FIND THE MASS PERCENTAGE OF THE ELEMENTS");
              System.out.println("ENTER 1 FOR YES AND 2 FOR NO");
              i=in.nextInt();
              k=0;
            }         
            else if(balanced==true&&simpleBalanced==false)
            {
              System.out.println("THE EQUATION IS {{UNBALANCED}} AS THE TOTAL NO. OF ATOMS ON BOTH SIDES ARE SAME BUT THE INDIVIDUAL ATOMS ARE VARYING ALSO");
            }
            
            else if(balanced==false&&simpleBalanced==false)
           {
              System.out.println("THE EQUATION IS {{UNBALANCED}} AS BOTH THE TOTAL NO. OF ATOMS AS WELL AS THE NO.OF ATOMS OF INDIVIDUAL ELEMENTS ARE DIFFERENT BUT");          
            }
            int r[]=new int[z+1];
              int sum=0;
              
           if(i==1)
           //to find mass percentage of elements in the compounds of lhsi.e.  in reactants side
             {
               for(Map.Entry<String, Integer> entry : parser.left.entrySet()) {
                  System.out.println("ENTER THE ATOMIC MASS OF THE ELEMENT "+entry.getKey());
                  r[k]=in.nextInt();
                  sum+=(r[k]*entry.getValue());
                  System.out.println("THE MOLECULAR MASS IS "+r[k]*entry.getValue());
                  k++;  
               }
               double x;
               k=0;
               for(Map.Entry<String, Integer> entry : parser.left.entrySet()) 
               {             
                  x=r[k]*100.0*entry.getValue()/sum;
                  System.out.println("THE MASS PERCENTAGE OF THE ELEMENT "+entry.getKey()+" IS "+x);
                  k++;
                }   
                System.out.print("Processing");
               for(i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            System.out.println();
            // to copy the given results
            System.out.println("Do u want the chemical equation with percentage.Enter 1 for yes else enter any other no.");
            x=in.nextInt();
            String gfd="";
            System.out.print("Processing");// to put a thread of processing
            for(i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            System.out.println();
            if(x==1)
            {
                k=0;
                gfd="Equation:"+args[0]+"\n";
               for(Map.Entry<String, Integer> entry : parser.left.entrySet()) 
               {             
                  x=r[k]*100.0*entry.getValue()/sum;
                  
                  gfd=gfd+("THE MASS PERCENTAGE OF THE ELEMENT "+entry.getKey()+" IS "+x+"\n");
                  k++;
                }
                // to copy the results
               StringSelection stringSelect=new StringSelection(gfd);
               Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
               clipboard.setContents(stringSelect,null);
               System.out.println("Sucessfully copied");
            }
               
               
            }
           else if(i==2)
            {
                 System.out.println("The Program Ends!!");
                 
            }
           else
            {
                 System.out.println("Invalid input");
                 
            }
         }
         
       else if(opt==2)
             {
            System.out.println("ENTER THE NAME OF PLANET WHOSE GRAVITATIONAL FORCE OR ACCELERATION DUE TO GRAVITY");
            String d=in.next();
            System.out.println("ENTER 1 IF YOU WANT TO CALCULATE THE GRAVITATIONAL FORCE AND 2 IF YOU WANT TO CALCULATE THE ACCELERATION DUE TO GRAVITY");
            int g1=in.nextInt();
            if(g1==1)
            {
                System.out.println("ENTER THE MASS OF OBJECT");
                double objmass=in.nextDouble();
                System.out.println("ENTER THE MASS OF PLANET IN THE FORM K*10^N");
                System.out.println("ENTER K:");
                double km=in.nextDouble();
                System.out.println("ENTER N:");
                int nk=in.nextInt();
                System.out.println(" RADIUS OF THE PLANET OF "+d+" IN THE FORM R1*10^TY ");
                System.out.println("ENTER R1:");
                double r1=in.nextDouble();
                System.out.println("ENTER TY:");
                int ty=in.nextInt();
                System.out.println("ENTER 1 IF THE OBJECT IS KEPT ON GROUND 2 IF THE OBJECT IS AT A HEIGHT ABOVE THE SURFACE OF THE PLANET "+d+" AND 3 IF THE OBJECT IS KEPT BELOW THE GROUND");
                int g=in.nextInt();
                if(g==1)
                {
                     gravity=objmass*6.673*Math.pow(10,-11)*km*Math.pow(10,nk)/Math.pow(r1*Math.pow(10,ty),2);
                }
                if(g==2)
                {
                    System.out.println("ENTER THE HEIGHT ABOVE THE GROUND");
                    double hag=in.nextInt();
                    gravity=objmass*6.673*Math.pow(10,-11)*km*Math.pow(10,nk)/Math.pow(r1*Math.pow(10,ty)+hag,2);
                }
                if(g==3)
                {
                    System.out.println("ENTER THE HEIGHT BELOW THE GROUND");
                    double hbg=in.nextInt();
                    gravity=6.673*Math.pow(10,-11)*km*Math.pow(10,nk)/Math.pow(r1*Math.pow(10,ty),2);
                    gravity*=objmass*(1-hbg/(r1*Math.pow(10,ty)));
                   
                }
            }
            if(g1==2)
            {
                System.out.println("ENTER THE MASS OF PLANET IN THE FORM K*10^N");
                System.out.println("Enter K:");
                double km=in.nextDouble();
                System.out.println("ENTER N:");
                int nk=in.nextInt();
                System.out.println(" RADIUS OF THE PLANET OF "+d+" IN THE FORM R1*10^TY ");
                System.out.println("ENTER R1:");
                double r1=in.nextDouble();
                System.out.println("ENTER TY:");
                int ty=in.nextInt();
                System.out.println("ENTER 1 IF THE OBJECT IS KEPT ON GROUND 2 IF THE OBJECT IS AT A HEIGHT ABOVE THE SURFACE OF THE PLANET "+d+" AND 3 IF THE OBJECT IS KEPT BELOW THE GROUND");
                int g=in.nextInt();
                if(g==1)
                {
                     gravity=6.673*Math.pow(10,-11)*km*Math.pow(10,nk)/Math.pow(r1*Math.pow(10,ty),2);
                }
                if(g==2)
                {
                    System.out.println("ENTER THE HEIGHT ABOVE THE GROUND");
                    double hag=in.nextInt();
                    gravity=6.673*Math.pow(10,-11)*km*Math.pow(10,nk)/Math.pow(r1*Math.pow(10,ty)+hag,2);
                }
                if(g==3)
                {
                    System.out.println("ENTER THE HEIGHT BELOW THE GROUND");
                    double hbg=in.nextInt();
                    gravity=6.673*Math.pow(10,-11)*km*Math.pow(10,nk)/Math.pow(r1*Math.pow(10,ty),2);
                    gravity*=(1-hbg/(r1*Math.pow(10,ty)));
                }
            }
            for(int i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            System.out.println();
            System.out.println(gravity);
            System.out.println("Do u want to copy the result.Enter 1 for yes else enter any other no.");
            int x=in.nextInt();
            String gfd="";
            System.out.print("Processing");
            for(int i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            System.out.println();
            // to copy the results
            if(x==1)
            {
                if(g1==1)
                {
                gfd=gravity+" N";
               }
               if(g1==2)
               {
                   gfd=gravity+"m/s^2";
                }
               StringSelection stringSelect=new StringSelection(gfd);
               Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
               clipboard.setContents(stringSelect,null);
               System.out.println("Sucessfully copied");
            }
            
        }
        
        else if(opt==3)
        {
            
            System.out.println("ENTER THE SR NO. FOR THE FUNCTION THAT FOLLOWS");
            System.out.println("1. NUMBER OF DIGITS IN THE NUMBERS AFTER ADDITION");
            System.out.println("2. NUMBER OF DIGITS IN THE NUMBERS AFTER SUBTRACTION");
            System.out.println("3. NUMBER OF DIGITS IN THE NUMBERS AFTER MULTIPLICATION");
            System.out.println("4. NUMBER OF DIGITS IN THE NUMBERS AFTER DIVISON");
            System.out.println("5. NUMBER OF DIGITS IN THE NUMBERS AFTER HCF");
            System.out.println("6. NUMBER OF DIGITS IN THE NUMBERS AFTER LCM");
            System.out.println("7. NUMBER OF DIGITS IN THE NUMBERS AFTER EXPONENTATION");
            System.out.println("8. NUMBER OF DIGITS IN THE NUMBERS AFTER FACTORIAL");
            System.out.println("9. TO SOLVE n SIMULTANEOUS EQUATIONS WITH n VARIABLES   ");
            int srno=in.nextInt();
            int no1,no2,ok=1,min,mod1,mod2,i; 
            // to calculate the no. of digits by common logarithms
            switch(srno)
            {
                case 1:
                System.out.println("ENTER 2 NOS.");
                no1=in.nextInt();
                no2=in.nextInt();
                ok=no1+no2;
                ok=(int)(Math.log10(ok));
                sim="addition";
                break;
                case 2:
                System.out.println("ENTER 2 NOS.");
                no1=in.nextInt();
                no2=in.nextInt();
                ok=no1-no2;
                ok=(int)(Math.log10(ok));
                sim="subtraction";
                break;
                case 3:
                System.out.println("ENTER 2 NOS.");
                no1=in.nextInt();
                no2=in.nextInt();
                ok=(int)(Math.log10(no1)+Math.log10(no2));
                sim="multiplication";
                break;
                case 4:
                System.out.println("ENTER 2 NOS.");
                no1=in.nextInt();
                no2=in.nextInt();
                ok=(int)(Math.log10(no1)-Math.log10(no2));
                sim="division";
                break;
                case 5:
                System.out.println("ENTER 2 NOS.");
                no1=in.nextInt();
                no2=in.nextInt();
                min=Math.min(no1,no2);
                for(i=1;i<=min;i++)
                {
                    mod1=no1%i;
                    mod2=no2%i;
                    if(mod1==mod2&&mod1==0)
                    {
                        ok=i;
                    }
                }
                ok=(int)(Math.log10(ok));
                sim="h.c.f";
                break;
                case 6:
                System.out.println("ENTER 2 NOS.");
                no1=in.nextInt();
                no2=in.nextInt();
                min=Math.min(no1,no2);
                for(i=1;i<=min;i++)
                {
                    mod1=no1%i;
                    mod2=no2%i;
                    if(mod1==mod2&&mod1==0)
                    {
                        ok=i;
                    }
                }
                ok=no1*no2/ok;
                ok=(int)(Math.log10(ok));
                sim="l.c.m";
                break;
                case 7:
                System.out.println("ENTER 2 NOS. FIRST NO. AND THEN EXPONENT");
                no1=in.nextInt();
                no2=in.nextInt();
                ok=(int)(no2*Math.log10(no1));
                sim="EXPONENTATION";
                break;
               
                case 8:
                System.out.println("ENTER A NO.");
                no1=in.nextInt();
                ok=(int)Math.log(Math.pow(2.0*22.0*no1/7.0,1.0/2.0)*Math.pow(no1,no1)/Math.exp(no1));
                sim="factorial";
                break;
                
                case 9:
                break;
                default:
                System.out.println("INVALID SR NO.");
                
            }
            if(srno==9)
            {
            char []var = {'x', 'y', 'z', 'w'};
            System.out.println("Enter the number of variables in the equations: ");
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            System.out.println("Enter the coefficients of each variable for each equations");
            System.out.println("ax + by + cz + ... = d");
            double [][]mat = new double[n][n];
            double [][]constants = new double[n][1];
            //input
           
            for(i=0; i<n; i++)

            {
                 System.out.println("enter for "+(i+1)+"equation");
                for(int j=0; j<n; j++)

                {
                    System.out.println("Enter the coefficient of "+(j+1)+" variable");
                    mat[i][j] = input.nextDouble();
                }
                System.out.println("Enter the constant");
                constants[i][0] = input.nextDouble();
                System.out.println((i+1)+"Equation is accepted ");
            }
            //Matrix representation
            System.out.print("Processing");
            for(i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            for(i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    System.out.print(" "+mat[i][j]);
                }
                System.out.print("  "+ var[i]);
                System.out.print("  =  "+ constants[i][0]);
                System.out.println();
            }
            //inverse of matrix mat[][]
            double inverted_mat[][] = invert(mat);
            System.out.println("The inverse is: ");
            for (i=0; i<n; ++i) 
            {
                for (int j=0; j<n; ++j)
                {
                    System.out.print(inverted_mat[i][j]+"  ");
                }
                System.out.println();
            }
            //Multiplication of mat inverse and constants
            double result[][] = new double[n][1];
            for (i = 0; i < n; i++) 
            {
                for (int j = 0; j < 1; j++) 

                {
                    for (int k = 0; k < n; k++)
                    {    
                        result[i][j] = result[i][j] + inverted_mat[i][k] * constants[k][j];
                    }
                }
            }
            System.out.println("The  is:");
            for(i=0; i<n; i++)
            {
                System.out.println("THE VALUE OF VARIABLE "+(i+1)+" IS "+result[i][0] + " ");
            }
            System.out.println("Do u want to copy the answers of variable.Enter 1 for yes else enter any other no.");
            int x=in.nextInt();
            String gfd="";
            System.out.print("Processing");
            for(i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            System.out.println();
            if(x==1)
            {
                for(i=0; i<n; i++)
               {
                gfd=gfd+" VARIABLE "+Integer.toString(i+1)+"="+Double.toString(result[i][0]) + " ";
               }
               // to copy the results
               StringSelection stringSelect=new StringSelection(gfd);
               Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
               clipboard.setContents(stringSelect,null);
               System.out.println("Sucessfully copied");
            }
            }
            if(srno<9&&srno>0)
            {
              System.out.println("THE NUMBER OF DIGITS AFTER "+sim.toUpperCase()+" ARE "+(++ok));
             }
             else
            {
                System.out.println("invalid input");
                
            }
      
      }
      else if(opt==4)
     {
             int z=0;
             // to calculate the number of days with simple algebra
            System.out.println("Enter the birth date");
            int a=in.nextInt();
            System.out.println("Enter the birth month");
            int b=in.nextInt();
            System.out.println("Enter the birth year");
            int c=in.nextInt();
            System.out.println("Enter today's date");
            int d=in.nextInt();
            System.out.println("Enter today's month");
            int e=in.nextInt();
            System.out.println("Enter today's year");
              int f=in.nextInt();
             int k=12+b-e,s=0,month_difference=0,days=0;
             if(a==d&&b==e)
             {
              System.out.println("happy birthday");
             }
            else
             {
             int year_difference=f-c-1;
            if(e==b&&a<d)
            {
               year_difference++;
               month_difference=0;
            }
             
            if(e==b&&a>d)
             {
               month_difference=12;
             }
             
             if(e>b)
             {
               year_difference++;
               month_difference=e-b;
            }
            
             if(e<b)
             {
               month_difference=12+e-b;
            }
            
            if(a<d)
             {
               days=d-a;
            }
            
            if(a>d)
            {
               days=a-d;
            }
            System.out.println("you lived "+days+" days "+month_difference+" months "+year_difference+" years");
             System.out.println("Do u want to copy the number of days you live.Enter 1 for yes else enter any other no.");
            int x=in.nextInt();
            String gfd="";
            System.out.print("Processing");
            for(int i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            System.out.println();
            if(x==1)
            {
                // to copy the results
                gfd=days+" days "+month_difference+" months "+year_difference+" years";
               StringSelection stringSelect=new StringSelection(gfd);
               Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
               clipboard.setContents(stringSelect,null);
               System.out.println("Sucessfully copied");
          }
        }
        }
       else if(opt==5)
          {  System.out.println("Enter a time");
             int h=in.nextInt();
             int m=in.nextInt();
             System.out.println(calcAngle(h,m)+" degree");
             // to call the function calcAngle()
             // to copy the results
              System.out.println("Do u want to copy the angle.Enter 1 for yes else enter any other no.");
            int x=in.nextInt();
            String gfd="";
            System.out.print("Processing");
            for(int i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            System.out.println();
            if(x==1)
            {
                gfd=Integer.toString(calcAngle(h,m));
               StringSelection stringSelect=new StringSelection(gfd);
               Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
               clipboard.setContents(stringSelect,null);
               System.out.println("Sucessfully copied");
            }
             
            }
        else if(opt==6)
        {
            System.out.println("Enter the date");
            int d=in.nextInt();
            int m=in.nextInt();
            int y=in.nextInt();
            String t[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
            int k3[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 }; 
            y -= (m < 3) ? 1 : 0; 
            int z1=(y + y/4 - y/100 + y/400 + k3[m-1] + d) % 7;
            System.out.println("The day is"+t[z1]);
             System.out.println("Do u want to copy the Day with date.Enter 1 for yes else enter any other no.");
            int x=in.nextInt();
            String gfd="";
            System.out.print("Processing");
            for(int i=1;i<=3;i++)
            {
            System.out.print(".");
            Thread.sleep(1000);
            }
            System.out.println();
            if(x==1)
            {
               // to copy the results
                gfd=d+"/"+m+"/"+y+" : "+t[z1];
               StringSelection stringSelect=new StringSelection(gfd);
               Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
               clipboard.setContents(stringSelect,null);
               System.out.println("Sucessfully copied");
            }
            
        }
         else
        {
            System.out.println("INVALID INPUT");
            
        }
       
       

     System.out.println("Enter 1 if you want to restart else enter any other no.");
     int lon=in.nextInt();
     if(lon==1)
     {
         System.out.print("\f");
         sdf=true;
         
        }
        else
        {
            sdf=!sdf;
            System.out.print("\f");
            String creater="Created by Raj R. Bunsha\nStd: Xth C\nRoll No.:28";// to display the name at the end of program
            for(int i=0;i<creater.length();i++)
            {
                System.out.print(creater.charAt(i));
                Thread.sleep(250);
            }
           System.exit(2);
        }
     }
     }
    
   static int calcAngle(double h, double m) 
    { 
        // validate the input 
        if (h <0 || m < 0 || h >12 || m > 60) 
            System.out.println("Wrong input"); 
  
        if (h == 12) 
            h = 0; 
        if (m == 60)  
            m = 0; 
  
        // Calculate the angles moved by hour and minute hands 
        // with reference to 12:00 
        int hour_angle = (int)(0.5 * (h*60 + m)); 
        int minute_angle = (int)(6*m); 
  
        // Find the difference between two angles 
        int angle = Math.abs(hour_angle - minute_angle); 
  
        // smaller angle of two possible angles 
        angle = Math.min(360-angle, angle); 
  
        return angle; 
   } 
      
    
        public static double[][] invert(double a[][]) 
   {
            int n = a.length;
            double x[][] = new double[n][n];
            double b[][] = new double[n][n];
            int index[] = new int[n];
            for (int i=0; i<n; ++i) 

                b[i][i] = 1;
            // Transform the matrix into an upper triangle
            gaussian(a, index);
            // Update the matrix b[i][j] with the ratios stored
            for (int i=0; i<n-1; ++i)

                for (int j=i+1; j<n; ++j)

                    for (int k=0; k<n; ++k)

                        b[index[j]][k]

                                -= a[index[j]][i]*b[index[i]][k];
            // Perform backward substitutions
            for (int i=0; i<n; ++i) 
            {
                x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
                for (int j=n-2; j>=0; --j) 
                {
                    x[j][i] = b[index[j]][i];
                    for (int k=j+1; k<n; ++k) 
                    {
                        x[j][i] -= a[index[j]][k]*x[k][i];
                    }
                    x[j][i] /= a[index[j]][j];
                }
            }
            return x;
        }
        
         // Method to carry out the partial-pivoting Gaussian
         // elimination.  Here index[] stores pivoting order.   
        public static void gaussian(double a[][], int index[]) 
        {
            int n = index.length;
            double c[] = new double[n];
            // Initialize the index
            for (int i=0; i<n; ++i) 

                index[i] = i;
            // Find the rescaling factors, one from each row
            for (int i=0; i<n; ++i) 
            {
                double c1 = 0;
                for (int j=0; j<n; ++j) 
                {
                    double c0 = Math.abs(a[i][j]);
                    if (c0 > c1) 
                    c1 = c0;
                }
                c[i] = c1;
            }
            // Search the pivoting element from each column
            int k = 0;
            for (int j=0; j<n-1; ++j) 
            {
                double pi1 = 0;
                for (int i=j; i<n; ++i) 
                {
                    double pi0 = Math.abs(a[index[i]][j]);
                    pi0 /= c[index[i]];
                    if (pi0 > pi1) 
                    {
                        pi1 = pi0;
                        k = i;
                    }
                }
                // Interchange rows according to the pivoting order
                int itmp = index[j];
                index[j] = index[k];
                index[k] = itmp;
                for (int i=j+1; i<n; ++i)   
                {
                    double pj = a[index[i]][j]/a[index[j]][j];
                    // Record pivoting ratios below the diagonal
                    a[index[i]][j] = pj;
                  // Modify other elements accordingly
                    for (int l=j+1; l<n; ++l)

                        a[index[i]][l] -= pj*a[index[j]][l];
                }
            }
    }
}

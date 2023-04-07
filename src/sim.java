import java.util.Random;

public class sim
{
    private double t0 = 1;
    private double nt = 100;
    private double rt = 0.99;

    public double f(double x)
    {
        return 10 * Math.sin((0.3 * x) * Math.sin(1.3 * Math.pow(x, 2) + 0.00001 * Math.pow(x, 4) + 0.2 * x + 80));
    }

    public double recuit(double x0)
    {
        double x = x0;
        double t = this.t0;
        double nt = this.nt;

        while(!(f(x) <= -10.0 || t < 0.01))//t >= Math.pow(10, -6) * this.t0
        {
            for(int m = 0; m < nt; m++)
            {
                double y = voisin(x);
                double df = f(y) - f(x);

                if(accepte(df, t)) x = y;
            }
            t = decroissance(t);
        }

        return x;

    }

    public double voisin(double x)
    {
        double x2;

        do{
            x2 = x+2*( Math.random() - 0.5);
        }
        while(x2 <= -10.0 || x2 > 10.0);

        return x2;

        //double random = Math.random() * (10 + 10) - 10;

        //return x + random;

		/*Random random = new Random();
		double res = random.nextDouble();

		return x + (10 + (res * (-10-10)));*/
    }

    public boolean accepte(double df, double t)
    {
        if(df >= 0)
        {
            double a = Math.exp(-df / t);

            if(Math.random() >= a) return false;
        }

        return true;
    }

	/*public double calculerRt()
	{
		this.rt = 1.0 / this.nt;
		this.rt = Math.pow(10, -6) * rt;
	}

	public double calculerT0()
	{

	}*/

    public double decroissance(double t)
    {
        return t * 0.999;
    }

    public static void main(String[] args)
    {
        sim rs = new sim();

        System.out.println(rs.recuit(0));

        for(int i = -10; i < 10; i++)
        {
            System.out.println(String.format(" la solution est %.2f", rs.f(rs.recuit(i))));
        }
    }
}
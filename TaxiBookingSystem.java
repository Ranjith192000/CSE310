import java.util.*;
import java.io.*;
interface Taxi
{
    void price();
}

abstract class Passenger
{
    abstract void getDetails();
    abstract void printDetails();
}

class Sedan implements Taxi
{
    public void  price()
    {
        int Bill;
        Bill = 50;
        System.out.println("Total Bill: "+Bill);
    }
}
class PrimeSedan implements Taxi
{
    public void price()
    {
        int Bill;
        Bill = 75;
        System.out.println("Total Bill: "+Bill);
    }
}
class AutoRickshaw implements Taxi
{
    public void price()
    {
        int Bill;
        Bill = 25;
        System.out.println("Total Bill: "+Bill);

    }
}
class Customer extends Passenger
{
    String Name,Mail,MyMobile,Ride,option;
    String pickup,droppoint,drop;
    String regex1 = "(0/91)?[7-9][0-9]{9}";
    String regex2 = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    long Mobile;

    void getDetails() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Enter Your Name: ");
        Name = Sc.nextLine();
        System.out.println("Enter Your Mobile Number:");
        Mobile = Sc.nextLong();
        MyMobile = Long.toString(Mobile);
        Sc.nextLine();
        System.out.println("Enter Your Email Id:");
        Mail = Sc.nextLine();
        System.out.println("\t\t\t\t\tEnter Your Pick Up Point");
        pickup = Sc.nextLine();
        System.out.println("\t\t\t\t\tEnter Your Drop Point");
        System.out.println("Enter 1 For Airport\tEnter 2 For Bus Station\tEnter 3 For Railway Station");
        droppoint = Sc.nextLine();
        System.out.println("\t\t\t\t\tEnter Type Of Your Ride:");
        System.out.println("Enter 1 For AutoRickshaw\tEnter 2 For Sedan\tEnter 3 For PrimeSedan");
        option = Sc.nextLine();
    }

    void printDetails() {
        try{
            FileWriter fw = new FileWriter("Booking.txt");
            PrintWriter pw = new PrintWriter(fw);
            Sedan S = new Sedan();
            PrimeSedan PS = new PrimeSedan();
            AutoRickshaw AR = new AutoRickshaw();
            Random rand = new Random();
            System.out.println("\t\t\tYour Ride Details");
            int rand_int1 = rand.nextInt(10000);
            pw.println("Booking Id: "+rand_int1);
            pw.println("Name: " + Name);
            if(MyMobile.matches(regex1) && Mail.matches(regex2))
            {
                pw.println("Mobile: " + MyMobile);
                pw.println("MailID:"+ Mail);
                pw.println("Pickup:"+pickup);
                if (droppoint.equals("1"))
                {
                    drop = "Airport";
                    pw.write("Drop:"+drop+"\n");

                }
                else if (droppoint.equals("2"))
                {
                    drop = "Bus Station";
                    pw.write("Drop:"+drop+"\n");

                }

                else if (droppoint.equals("3"))
                {
                    drop = "Railway Station";
                    pw.write("Drop:"+drop+"\n");

                }
                else
                {
                    System.out.println("Only These Three Drop Points Are Available");
                }
                if (option.equals("1")) {
                    Ride = "AutoRickshaw";
                    pw.println("Ride: " + Ride);
                    AR.price();
                } else if (option.equals("2")) {
                    Ride = "Sedan";
                    pw.println("Ride: " + Ride);
                    S.price();
                } else if (option.equals("3")) {
                    Ride = "PrimeSedan";
                    pw.println("Ride: " + Ride);
                    PS.price();

                } else {
                    System.out.println("Only Enter Available Rides");
                }
                pw.close();

                FileReader fr = new FileReader("Booking.txt");
                BufferedReader br = new BufferedReader(fr);

                String str;
                while ((str = br.readLine()) != null)
                {
                    System.out.println(str);
                }
                br.close();
                System.out.println("\t\t\t\t\tBooking Successful");
            }
            else
            {
                System.out.println("Invalid Mobile no or EmailId ");
                getDetails();
                printDetails();
            }
         }

        catch(IOException e)
        {
            System.out.println("Please Book Your Taxi");
        }

    }

}
class AddandDelRide
{
    void showRide()
    {
        try
        {
            FileReader fr = new FileReader("Booking.txt");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null)
            {
                System.out.println(str);
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Please Book Your Taxi");
        }
    }
    void cancelRide()
    {
        File file = new File("Booking.txt");

        if(file.delete())
        {
            System.out.println("Ride Cancelled successfully");
        }
        else
        {
            System.out.println("Failed to Cancel the Ride");
        }
    }
}
class TaxiBookingSystem
{
    public static void main(String[] args)
    {
        Scanner In = new Scanner(System.in);
        Passenger P = new Customer();
        AddandDelRide AD = new AddandDelRide();
        System.out.println("\t\t\t\t\tTaxi Management System");
        System.out.println("Enter 1 To Book Ride\tEnter 2 To Cancel Ride\tEnter 3 To View Ride");
        String choice = In.nextLine();
        if(choice.equals("1"))
        {
            P.getDetails();
            P.printDetails();
        }
        else if (choice.equals("2"))
        {
            AD.cancelRide();
        }
        else if(choice.equals("3"))
        {
            System.out.println("\t\t\t\t\tThis Will Only Show Last Booked Ride");
            AD.showRide();
        }
        else
        {
            System.out.println("Please Enter Available Option");
        }

    }

}

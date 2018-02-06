package id.co.betalogika.ikeni;

public class Ikeni
{
    private static Ikeni sIkeni;

    private Ikeni ()
    {

    }

    public static Ikeni can ()
    {
        if (sIkeni == null)
        {
            sIkeni = new Ikeni();
        }
        return sIkeni;
    }


}

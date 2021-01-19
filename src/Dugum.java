public class Dugum
{  //dugumun en onemlı kısmı gırdılerın verilen duzenın saglanması ıcın kullanılan ana kısım 
    String isim;
    int yukseklık;
    Dugum sol;
    Dugum sag;

    public Dugum()
    {
        this.isim = new String("Value");
        this.yukseklık = 1;
        this.sol = null;
        this.sag = null;
    }

    public Dugum(String isim)
    {
        this.isim = new String(isim);
        this.yukseklık = 1;
        this.sol = null;
        this.sag = null;
    }
}


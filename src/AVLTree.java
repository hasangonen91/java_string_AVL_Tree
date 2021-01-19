import java.util.Stack;

public class AVLTree
{
    static  Dugum Kok;

    int yukseklıkAl(Dugum key)//yukseklık belirttik 
    {
        if (key == null)
           return 0;

        else
            return key.yukseklık;
    }


    int DengeAl(Dugum key)// Denge belirliyoruz
    {
        if (key == null)
           return 0;

        else
            return (yukseklıkAl(key.sag) - yukseklıkAl(key.sol));
    }


    void YeniYukseklık(Dugum key)// yukseklığgı guncellıyoruz
    {
        int SolCocukYuseklıgı = yukseklıkAl(key.sol);
        int SagCocukYuseklıgı = yukseklıkAl(key.sag);

        key.yukseklık = Math.max(SolCocukYuseklıgı , SagCocukYuseklıgı) + 1;
    }


    Dugum SolaBak(Dugum eskiKok)//soldan kontrol edıyoruz
    {
        Dugum yeniKok = eskiKok.sag;
        Dugum temp = yeniKok.sol;

        yeniKok.sol = eskiKok;
        eskiKok.sag = temp;

        YeniYukseklık(eskiKok);
        YeniYukseklık(yeniKok);

        return yeniKok;
    }



    Dugum SagBak(Dugum eskiKok)//sagdan kontrol edıyoruz
    {
        Dugum yeniKok = eskiKok.sol;
        Dugum temp = yeniKok.sag;

        yeniKok.sag = eskiKok;
        eskiKok.sol = temp;

        YeniYukseklık(eskiKok);
        YeniYukseklık(yeniKok);

        return yeniKok;
    }



    Dugum AgacDenge(Dugum Kok)// dugum sıralı olabilsindiye denge
    {
        YeniYukseklık(Kok);

        int denge = DengeAl(Kok);

        if (denge == 2)
        {
            if (DengeAl(Kok.sag) < 0)
                Kok.sag = SagBak(Kok.sag);

            return SolaBak(Kok);
        }

        if (denge == -2)
        {
            if (DengeAl(Kok.sol) > 0)
                Kok.sol = SolaBak(Kok.sol);

            return SagBak(Kok);
        }

        return Kok;
    }


    Dugum KokEkle(Dugum Kok, String key)// bu kısımda ise dugumu buyutuyoruz eleman ekleme vs.
    {
        if (Kok == null)
            return new Dugum(key);

        else if (key.compareToIgnoreCase(Kok.isim) < 0)
            Kok.sol = KokEkle(Kok.sol, key);

        else
            Kok.sag = KokEkle(Kok.sag, key);

        return AgacDenge(Kok);
    }


    Dugum AtaBul(Dugum Kok) // arama kısmı
    {
        if (Kok.sol != null)
            return AtaBul(Kok.sol);

        else
            return Kok;
    }


    Dugum DugumSil(Dugum Kok, String key)// dugumden eleman silme kısmı 
    {
        if (Kok == null)// tabi once elemanın bulunması gerekiyor
            return Kok;

        else if (key.compareToIgnoreCase(Kok.isim) < 0)// soldan
            Kok.sol = DugumSil(Kok.sol, key);

        else if (key.compareToIgnoreCase(Kok.isim) > 0)// sagdan kontrol ediyoruz nerede diye 
            Kok.sag = DugumSil(Kok.sag, key);

        else
        {
            if (Kok.sag == null)
                Kok = Kok.sol;
// ondan sonrada siliyoruz
            else if (Kok.sol == null)
                Kok = Kok.sag;

            else
            {
                Dugum temp = AtaBul(Kok.sag);
                Kok.isim = temp.isim;
                Kok.sag = DugumSil(Kok.sag, Kok.isim);
            }
        }

        if (Kok == null)
            return Kok;

        else
            return AgacDenge(Kok);
    }


    Dugum Ara(Dugum Kok, String key) // bu kısım arama bulma kısmı true ya da false diye cevap verilen kısım
    {
        if (Kok == null || key.compareToIgnoreCase(Kok.isim) == 0)// aranan deger  bu kısımlarda aranıyor
            return Kok;

        if (key.compareTo(Kok.isim) < 0)
            return Ara(Kok.sol, key);

        else
            return Ara(Kok.sag, key);
    }
    
    /////////////////////////////////////////////////////////////
   
   
////////////////////////////////////////////////////////////////////////////////
    void Ekle(String key)  // dugume eleman ekledigimiz ana kısım 
    {
        if (Ara(Kok , key) == null)// kontrol edilen ksıım da ise varsa var yoksa yok der
        {
            Kok = KokEkle(Kok , key);
            System.out.println("\n" + key + " Eklendi.");
        }

        else
            System.out.println("\n" + key + " Zaten var.");
    }


    void Sil(String key) //dugumden eleman silmek istedigimiz kısım
    {
        if (Ara(Kok , key) != null)// kontrol ediyoruz varmı yokmu diye 
        {
            Kok = DugumSil(Kok , key); // varsa silinir
            System.out.println("\n" + key + " Silindi.)");
        }

        else// yoksa silinemez
            System.out.println("\n" + key + " Listede yok.");
    }


    int Sıra(String key)
    {//girilen elemanın sırası
        Dugum temp = Ara(Kok, key);

        if (temp == null)
            return -1;

        else
        {
            System.out.println("\n" + "Girilen " + key + " girdisinin sırası " + (Kok.yukseklık - temp.yukseklık));
            return (Kok.yukseklık - temp.yukseklık);
        }
    }


    void PreOrder(Dugum key)
    { //  düzen gösterdigimiz kısım
        System.out.print("\nPreOrder  : ");
        Stack <Dugum> stack = new Stack <Dugum>();

        if (key == null)
            return;

        else
        {
            stack.push(key);

            while (!stack.empty())
            {
                key = stack.pop();
                System.out.print(key.isim + " ");

                if (key.sag != null)
                    stack.push(key.sag);

                if (key.sol != null)
                    stack.push(key.sol);
            }
        }

        System.out.println();
    }


    void InOrder(Dugum key)
    {// yukarıdakinden farkı sıra ile gosterdigi kısım
        System.out.print("\nInOrder  : ");
        System.out.print("\n");
        Stack <Dugum> stack = new Stack <Dugum>();

        while (!stack.empty() || key != null)
        {
            if (key != null)
            {
                stack.push(key);
                key = key.sol;
            }

            else
            {
                key = stack.pop();
                System.out.print(key.isim + " ");
                key = key.sag;
            }
        }

        System.out.println();
    }



    
}
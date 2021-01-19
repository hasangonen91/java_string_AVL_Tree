import java.util.*;
 // AVL agac test 
public class AVLTreeTest{
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // AVL Ağacı nesnesinin oluşturulması 
        AVLTree avl = new AVLTree();
        System.out.println("********AVLTREE******");
        // Ağaç işlemlerini gerçekleştirilmesi 
        do    
        {
            System.out.println("****AVLTree İşlemleri***");
            System.out.println("*** 1. EKLE ***");
            System.out.println("*** 2. ARA  ***");
            System.out.println("*** 3. SİL  ***");
            System.out.println("YUKARIDAKİ İŞLEMLERDEN BİRİNİ SEÇİNİZ:");
            String sec = input.nextLine();
            String key;
            switch (sec) {
                case "1":
                    System.out.println("*Eklenecek ögeyi/kelimeyi/karakteri girin:    ");
                    avl.Ekle(input.nextLine());
                    break;
                case "2":
                    System.out.println("*Aramak için ögeyi/kelimeyi/karakteri girin:    ");
                    if (avl.Ara(AVLTree.Kok,input.nextLine())==null) {// bu sartlar sayesinde aranan eleman var ıse true yoksa false diye deger donduruyor
                        System.out.println("*Arama sonuçları :" + false );
                    }
                    else{
                        System.out.println("*Arama sonuçları :" + true );
                    }
                    break;
                case "3":
                    System.out.println("*silinecek tam sayı öğesini girin:   ");
                   avl.Sil(input.nextLine());
                    break;
                default:

                    System.out.println("\n\t*Hatali Giris Yaptiniz..");
                    break;
            }
            /* Ekran ağacı */ 
  
            avl.PreOrder(avl.Kok);
            avl.InOrder(avl.Kok);
            
        }while (true);           
    }
 }
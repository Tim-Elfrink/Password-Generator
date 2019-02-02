import java.util.Scanner;

class PasswordGenerator{
  Scanner scn = new Scanner(System.in);
  
  String PublicKey;
  String PublicString = "";
  int PublicInt;
  int PrivKey1;
  int PrivKey2;
  int PrivTotal;
  String PrivTotalString;
  String NewPass = "";
  String Alphabet = "abcdefghijklmnopqrstuvwxyz";
  String Characters = "AV-;a_B!bCvWZMmf-GO.eFRgHzcD*dENnuxY:hIi?JjKrS,sTtUkLloPwXypQq|@";
 
  
  public void Generate(){
    
    // Asks the user for values used to generate a random-looking password
    System.out.println("Type the name of the service you wish to generate a new password for (lowercase)");
      PublicKey = scn.next();
    System.out.println("Type a large number that you have to memorise");
      PrivKey1 = scn.nextInt();
    System.out.println("Type the date on which this password was last modified (ddmmyyyy)");
      PrivKey2 = scn.nextInt();
    
    
    
    
    
    // Converts the string PublicKey to a numerical value
    for(int i = 0; i < PublicKey.length(); i++){
      for(int n = 0; n < Alphabet.length(); n++){
        if(PublicKey.substring(i, i+1).equals(Alphabet.substring(n, n+1))){
          PublicString = PublicString + n;
        }
      } 
    }
    PublicInt = Integer.valueOf(PublicString); 
    
    
    
    
    // Takes the values and creates the password

    // basic encryption technique
    PrivTotal =(PublicInt^PrivKey1)%PrivKey2;
    PrivTotalString = "" + PrivTotal + PrivTotal;
    
    //inserts letters and characters based on PrivTotalString
    for(int k = 0; k < PrivTotalString.length()/2; k++){
      if(Integer.valueOf(PrivTotalString.substring(k, k+7))%128 < 64){
        NewPass = NewPass + Characters.substring(k, k+1);
        if(Integer.valueOf(PrivTotalString.substring(k, k+6))%128 < 64) {
          NewPass = NewPass + Characters.substring(PrivKey1%64, (PrivKey1%64)+1); 
          if (Integer.valueOf(PrivTotalString.substring(k, k+5))%128 < 64){
            NewPass = NewPass + Characters.substring(PrivKey2%64, (PrivKey2%64)+1);
          }
        }
      }
      NewPass = NewPass + PrivTotalString.substring(k, k+1);
    }
    System.out.println(NewPass);
  }


public static void main(String...args) {
  PasswordGenerator newPassword = new PasswordGenerator();
  newPassword.Generate();
  }
}

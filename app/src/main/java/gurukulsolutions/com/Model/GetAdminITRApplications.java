package gurukulsolutions.com.Model;

public class GetAdminITRApplications {
    private String Tax_Payer_Name,PhoneNumber,Email,AADHAAR_number,PAN_Card;
    private String Loan_Type,pid;

    public GetAdminITRApplications() {
    }

    public GetAdminITRApplications(String tax_Payer_Name, String phoneNumber, String email, String AADHAAR_number, String PAN_Card, String loan_Type, String pid) {
        Tax_Payer_Name = tax_Payer_Name;
        PhoneNumber = phoneNumber;
        Email = email;
        this.AADHAAR_number = AADHAAR_number;
        this.PAN_Card = PAN_Card;
        Loan_Type = loan_Type;
        this.pid = pid;
    }

    public String getTax_Payer_Name() {
        return Tax_Payer_Name;
    }

    public void setTax_Payer_Name(String tax_Payer_Name) {
        Tax_Payer_Name = tax_Payer_Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAADHAAR_number() {
        return AADHAAR_number;
    }

    public void setAADHAAR_number(String AADHAAR_number) {
        this.AADHAAR_number = AADHAAR_number;
    }

    public String getPAN_Card() {
        return PAN_Card;
    }

    public void setPAN_Card(String PAN_Card) {
        this.PAN_Card = PAN_Card;
    }

    public String getLoan_Type() {
        return Loan_Type;
    }

    public void setLoan_Type(String loan_Type) {
        Loan_Type = loan_Type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}

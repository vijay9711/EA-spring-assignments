package bank.dto;

public class CustomerDTO {
    private String name;

    public CustomerDTO(String name) {
        this.name = name;
    }
    public CustomerDTO() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

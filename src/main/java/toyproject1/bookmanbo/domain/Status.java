package toyproject1.bookmanbo.domain;

public enum Status {
    FINISH("읽음"),
    ING("읽는중"),
    WISH("찜한책");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}

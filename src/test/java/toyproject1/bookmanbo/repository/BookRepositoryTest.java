package toyproject1.bookmanbo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import toyproject1.bookmanbo.BookmanboApplicationTests;
import toyproject1.bookmanbo.domain.entity.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class BookRepositoryTest extends BookmanboApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void create(){
        Book b1=new Book("1234",
                "알라딘 상품정보 - 자기신뢰",
                "랄프 왈도 에머슨 (지은이), 이종인 (옮긴이)",
                LocalDate.parse("2019-11-11"),
                "현대지성 클래식 36권. 니체가 말한 초인(超人)의 사상적 뿌리이자, 미국의 개척·독립정신의 초석이 된 에머슨의 에세이 3편이 꼼꼼한 해제와 가독성 높은 완역을 거쳐, 현대지성 클래식 제36권 『자기 신뢰』에 담겨 독자들과 만난다.",
                "https://image.aladin.co.kr/product/26820/59/coversum/k392730292_1.jpg",
                "국내도서>자기계발>성공>성공학",
                "현대지성",
                216
                );

        System.out.println(bookRepository.save(b1));

    }
    @Test
    @Transactional
    public void read(){
        List<Book> books =bookRepository.findAll();
        System.out.println(books);



    }
    public void update(){

    }
    public void delete(){

    }



}

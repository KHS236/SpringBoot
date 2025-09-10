package com.example.demo.Domain.Common.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="lend")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Id 만들면서 자동증가로 번호 부여하기
    private Long id;

    //다대1 관계를 나타내는 어노테이션
    @ManyToOne(fetch = FetchType.LAZY) //헷갈리거나 감이 안 오면 그냥 LAZY를 기본값으로
    @JoinColumn(
            name = "username", //표시할 외래키 컬럼명
            foreignKey =@ForeignKey(
                    name = "`FK_LEND_USER`",
                    //아래는 외래키 관련된 쿼리들 넣어주기
                    foreignKeyDefinition = "FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE"
            )

    )
    private User user;//유저엔티티에서 가져올 거야 (연결)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name="bookCode",
        foreignKey = @ForeignKey(
                name="FK_LEND_BOOK",/*아래 definition에 들어갈 bookCode는 참조외래키컬럼명과 일치해야함*/
                foreignKeyDefinition = "FOREIGN KEY (bookCode) REFERENCES book (bookCode) ON DELETE CASCADE ON UPDATE CASCADE)"
        )
    )
    private Book book;//북 엔티티 연결

    
    //랜드 레파지토리 만들기
}

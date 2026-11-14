package ma.enset.accountservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTrensaction {
    @Id @GeneratedValue
    private Long id;
    private LocalDateTime dateTime;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
}

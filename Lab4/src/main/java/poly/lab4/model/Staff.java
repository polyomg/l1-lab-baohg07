package poly.lab4.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    @NotBlank(message = "Chưa nhập email")
    @Email(message = "Email không đúng định dạng")
    private String id; // nếu đây là email thì đặt tên email sẽ rõ ràng hơn

    @NotBlank(message = "Chưa nhập họ và tên")
    private String fullName;

    @Builder.Default
    private String photo = "photo.jpg";

    @NotNull(message = "Chưa chọn giới tính")
    @Builder.Default
    private Boolean gender = true;

    @NotNull(message = "Chưa nhập ngày sinh")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday; // bỏ default = new Date()

    @NotNull(message = "Chưa nhập lương")
    @DecimalMin(value = "1000.0", message = "Lương tối thiểu phải là 1000")
    @Builder.Default
    private Double salary = 12345.6789;

    @Builder.Default
    private Integer level = 0;
}

package org.liko.study.springboot.session2.lombok;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Demo {
    private String name;
    private String code;
}

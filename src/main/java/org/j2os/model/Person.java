package org.j2os.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    Bahador, Amirsam
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Person {
    private String name;
    private String family;
}

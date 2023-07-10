package com.dahuatech.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>projectName: faceofflinedossierfix</p>
 * <p>packageName: com.dahua.tech.dossier.fix.entity</p>
 * <p>className: GPS</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPS {
    private double gpx;
    private double gpy;
}

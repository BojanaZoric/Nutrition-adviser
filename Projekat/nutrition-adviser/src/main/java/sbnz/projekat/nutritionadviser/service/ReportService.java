package sbnz.projekat.nutritionadviser.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.drools.template.ObjectDataCompiler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.dto.ReportDTO;

@Service
public class ReportService {
	

    private String resonerDirPath = "../nutrition-adviser-drools/src/main/resources/sbnz.projekat";

    @Value("${resonerPomPath}")
    private String resonerPomPath;
	

	 public void applyRuleTemplate(ReportDTO dto) throws Exception {
		InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("report.drt");

		dto.setNumber(UUID.randomUUID().toString());
		List<ReportDTO> data = new ArrayList<ReportDTO>();
		
		data.add(dto);
		
		ObjectDataCompiler converter = new ObjectDataCompiler();
		String drl = converter.compile(data, template);
		
		this.outputRule(drl, dto.getNumber());
	}
	
    private void outputRule(String drl, String ruleFileName) throws IOException {
        // create new file for new rule
        File dir = new File(this.resonerDirPath);
        if (!dir.exists()) dir.mkdirs();

        String fileName = ruleFileName.replaceAll(" ", "_").toLowerCase() + ".drl";

        File ruleFile = new File(this.resonerDirPath + "/" + fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruleFile))) {
            bw.append(drl);
        }
    }


}

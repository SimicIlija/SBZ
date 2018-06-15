package drools.spring.example;

import drools.spring.example.model.ReasonInput;
import drools.spring.example.model.Symptom;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SampleAppService {

    private final KieContainer kieContainer;

    @Autowired
    public SampleAppService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void testVisitRule() {
        Symptom symptom = new Symptom("Cesto uriniranje");
        ReasonInput input = new ReasonInput();
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Mucnina i povracanje");
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Zamor");
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Kasalj");
        input.getSymptoms().add(symptom);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("simptomi").setFocus();
        kieSession.fireAllRules();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("connected").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(input);
    }
}

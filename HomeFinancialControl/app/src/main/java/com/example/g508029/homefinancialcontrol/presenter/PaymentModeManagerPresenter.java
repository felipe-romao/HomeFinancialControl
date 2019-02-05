package com.example.g508029.homefinancialcontrol.presenter;

import com.example.g508029.homefinancialcontrol.DB.IPaymentModeRepository;
import com.example.g508029.homefinancialcontrol.helper.PaymentModeHelper;
import com.example.g508029.homefinancialcontrol.model.PaymentMode;

import java.util.List;
import java.util.UUID;

public class PaymentModeManagerPresenter {
    public interface IPaymentModeManagerView{
        String getMode();
        void setMode(String mode);
        void setPaymentModes(List<String> modes);
        void showMessage(String message);
    }

    private IPaymentModeManagerView view;
    private IPaymentModeRepository repository;

    public PaymentModeManagerPresenter(IPaymentModeManagerView view, IPaymentModeRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void initialize(){
        try {
            this.clearFields();
            this.onGetAllPaymentModes();
        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao inicializar: "  + ex.getMessage());
        }
    }

    public void onAddedPaymentMode(){
        try {
            this.validateValues();
            String id = UUID.randomUUID().toString();
            String description = this.view.getMode();
            PaymentMode paymentMode = new PaymentMode(id, description);
            this.repository.addPaymentMode(paymentMode);
            this.clearFields();
            this.view.showMessage("Mode de pagamento '"+ description + "' adicionado com sucesso!");
        }catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar adicionar: "  + ex.getMessage());
        }

    }

    private void validateValues() {
        if(this.view.getMode() == null || this.view.getMode().isEmpty()){
            throw new RuntimeException("Informe uma descrição para o modo de pagamento");
        }
    }

    public void onGetAllPaymentModes(){
        try {
            List<PaymentMode> paymentModes = this.repository.getAll();
            this.view.setPaymentModes(PaymentModeHelper.getAllDescriptionFromPaymentMode(paymentModes));

        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao listar os modos de pagamentos: " + ex.getMessage());
        }
    }

    private void clearFields(){
        this.view.setMode("");
    }
}

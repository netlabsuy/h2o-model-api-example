package uy.com.netlabs.h2omodelapi;

import hex.genmodel.MojoModel;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ModelExecutor {
    private static ModelExecutor instance = new ModelExecutor();

    private EasyPredictModelWrapper model;

    public static ModelExecutor getInstance() {
        return instance;
    }

    public void init(String path) {
        try {
            model = new EasyPredictModelWrapper(MojoModel.load(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> score(Map<String, Object> input) throws PredictException {
        RowData row = new RowData();

        for (Map.Entry<String, Object> entry : input.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().equals("NULL")) {
                row.put(entry.getKey(), entry.getValue().toString());
            }
        }

        BinomialModelPrediction p = model.predictBinomial(row);

        input.put("label", p.label);

        return input;
    }

    private ModelExecutor() { }
}

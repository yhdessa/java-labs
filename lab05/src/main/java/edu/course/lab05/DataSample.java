package edu.course.lab05;

public final class DataSample {

    private final String id;
    private final String label;
    private final String source;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, String source, SampleStatus status, double[] features) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("label must not be blank");
        }
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("source must not be blank");
        }
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("features must not be empty");
        }
        this.id = id;
        this.label = label;
        this.source = source;
        this.status = status;
        this.features = features.clone();
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getSource() {
        return source;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return features.clone();
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("newStatus must not be null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public int featureCount() {
        return features.length;
    }

    public double averageFeatures() {
        double sum = 0.0;
        for (double value : features) {
            sum += value;
        }
        return sum / features.length;
    }
}

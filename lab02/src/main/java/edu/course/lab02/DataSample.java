package edu.course.lab02;

public final class DataSample {
    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, SampleStatus status, double[] features) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException();
        } if (label == null || label.isBlank()) {
            throw new IllegalArgumentException();
        } if (status == null) {
            throw new IllegalArgumentException();
        } if (features == null || features.length == 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.label = label;
        this.status = status;
        this.features = features.clone();
    }
    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return features.clone();
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException();
        } status = newStatus;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double averageFeatures() {
        double sum = 0;
        for (double feature : features) {
            sum += feature;
        } return sum / features.length;
    }
}
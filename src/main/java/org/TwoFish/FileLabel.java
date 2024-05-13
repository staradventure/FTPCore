package org.TwoFish;

public class FileLabel {
    private String name;
    private String label;

    public FileLabel() {
    }

    public FileLabel(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "FileLabel{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}

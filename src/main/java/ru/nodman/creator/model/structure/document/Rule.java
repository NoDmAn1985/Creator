package ru.nodman.creator.model.structure.document;

public class Rule {
    private String name;
    private String title;

    public Rule(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (!name.equals(rule.name)) return false;
        return title.equals(rule.title);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}

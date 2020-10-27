package seedu.address.commons.dataset.tag;

import java.util.Objects;

import seedu.address.model.tag.Tag;

public class TagKey {
    private Tag tag;

    public TagKey(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return this.tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagKey tagKey = (TagKey) o;
        return tag.equals(tagKey.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}

package seedu.address.model.dataset.tag;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.tag.Tag;

public class TagKey {
    private Tag tag;

    /**
     * Creates a TagKey with the specified {@code tag}.
     * @param tag The tag to be stored in the TagKey.
     */
    public TagKey(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    public Tag getTag() {
        return this.tag;
    }

    public void setTag(Tag tag) {
        requireNonNull(tag);
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

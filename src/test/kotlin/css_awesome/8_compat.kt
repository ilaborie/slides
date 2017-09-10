package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.ExternalHtmlContent
import org.ilaborie.slides.content.ExternalResource
import org.ilaborie.slides.content.Link
import org.ilaborie.slides.content.h4


fun compat(group: Group) = group
        .slide(title = "Compat", styleClass = setOf("hide-title")) {
            Link("caniuse", "http://caniuse.com").h4() +
                    Link("The CSS3 / CSS4 Test", "http://css3test.com").h4() +
                    ExternalHtmlContent(ExternalResource("/cssIsAwesome/08_compat/support.html"))
        }
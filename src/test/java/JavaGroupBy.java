import model.BlogPost;
import model.BlogPostType;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Created by poojakhot on 6/4/19.
 */
public class JavaGroupBy {

    List<BlogPost> posts = Arrays.asList(new BlogPost("title1", "author1", BlogPostType.GUIDE, 2), new BlogPost("title1", "author2", BlogPostType.GUIDE, 2), new BlogPost("title2", "author2", BlogPostType.NEWS, 3));

    @Test
    public void test() {
        Map<BlogPostType, Map<String, List<BlogPost>>> postsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType,Collectors.groupingBy(BlogPost::getAuthor)));

        System.out.println(postsPerType.size());

        postsPerType.forEach((k,v)->v.forEach((k1,v1)->System.out.println(k1+ " "+v1)));


    }
}

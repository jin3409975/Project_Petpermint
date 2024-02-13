package com.ssafy.api.service;

import com.ssafy.api.request.*;
import com.ssafy.db.entity.*;
import com.ssafy.db.join.PostUrlList;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommunityRepositorySupport communityRepositorySupport;
    @Autowired
    CommentRepositorySupport commentRepositorySupport;

    @Autowired
    PostFilesRepository postFilesRepository;
    @Autowired
    PostLikesRepository postLikesRepository;

    @Autowired
    VideoRepository videoRepository;

    @Override
    public UserPost writePost(CommunityWritePostReq writeInfo) {

        UserPost userPost = new UserPost();
        userPost.setUserId(writeInfo.getUserId());
        userPost.setContent(writeInfo.getContent());


        return communityRepository.save(userPost);

    }

    @Override
    public UserPost dataPost(int postId) {
        UserPost userPost = communityRepositorySupport.findDataByPostId(postId).get();
        return userPost;
    }

    @Override
    public Long updatePost(CommunityUpdatePutReq updateInfo) {
        Long result=communityRepositorySupport.updatePostByPostId(updateInfo).get();
        return result;
    }

    @Override
    public Long isDeletePost(int postId) {
        Long result=communityRepositorySupport.isDeletePostByPostId(postId).get();
        return result;
    }

    @Override
    public Long deletePost(int postId) {
        Long result=communityRepositorySupport.deletePostByPostId(postId).get();
        return result;
    }

    @Override
    public PostComment createComment(CommentCreatePostReq createInfo) {
        PostComment postComment= new PostComment();
        postComment.setPostId(createInfo.getPostId());
        postComment.setUserId(createInfo.getUserId());
        postComment.setContent(createInfo.getContent());
        return commentRepository.save(postComment);
    }

    @Override
    public List<PostComment> dataComment(int postId) {
        List<PostComment> postComment = commentRepositorySupport.findCommentByPostId(postId).get();
        return postComment;
    }

    @Override
    public Long isDeleteComment(CommentDeleteDeleteReq deleteInfo) {
        Long result=commentRepositorySupport.isDeleteCommentByDeleteInfo(deleteInfo).get();
        return result;
    }

    @Override
    public Long deleteComment(CommentDeleteDeleteReq deleteInfo) {
        Long result=commentRepositorySupport.deleteCommentByDeleteInfo(deleteInfo).get();
        return result;
    }

    @Override
    public Long increaseLike(int postId) {
        Long result=communityRepositorySupport.updateLikeByPostId(postId).get();
        return result;
    }

    @Override
    public Long decreaseLike(int postId) {
        Long result=communityRepositorySupport.decreaseLikeByPostId(postId).get();
        return result;
    }

    @Override
    public Long increaseHit(int postId) {
        Long result=communityRepositorySupport.updateHitByPostId(postId).get();
        return result;
    }

    @Override
    public int findPostCount() {
        int count=communityRepositorySupport.findMaxPostId().get();
        return count+1;
    }

    @Override
    @Transactional

    public int writeUrl(int postId, List<String> fileNames,List<String> urls) {
        int i;
        for(i=0;i<urls.size(); i++) {
            PostFiles postFiles = new PostFiles();
            postFiles.setPostId(postId);
            postFiles.setUrl(urls.get(i));
            postFiles.setFileName(fileNames.get(i));
            try {
                System.out.println("posing: "+ postFiles.getFileName());

                postFilesRepository.save(postFiles);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        return i+1;
    }

    @Override
    public List<String> getUrl(int postId) {
        List<PostFiles> postFiles=postFilesRepository.findByPostId(postId);
        List<String> urls=new ArrayList<String>();
        for(PostFiles postFile: postFiles)
            urls.add(postFile.getUrl());
        return urls;
    }


    @Override
    @Transactional
    public int deleteUrl(int postId) {
        int delete_result= postFilesRepository.deleteByPostId(postId);
        return delete_result;
    }

    @Override
    public List<UserPost> listPost() {
        List<UserPost> posts=communityRepository.findByIsDelete(false);
        return posts;
    }

    @Override
    public List<PostFiles> listUrl() {
        List<PostFiles> urls=postFilesRepository.findAll();
        return urls;
    }

    @Override
    public List<PostUrlList> findPostUrlJoin(int page, List<Integer>postIds) {
        int startpg=page*2;
        int endpg=page*2+2;

        List<PostUrlList> result=communityRepository.findPostUrlJoin(page, postIds);
        return result;
    }

    @Override
    public PostLikes insertIntoLikeTable(int postId, String userId) {
        PostLikes postLikes= new PostLikes();
        postLikes.setUserId(userId);
        postLikes.setPostId(postId);

        return postLikesRepository.save(postLikes);
    }

    @Override
    public long deleteFromLikeTable(int postId, String userId) {
        return postLikesRepository.deleteByPostIdAndUserId(postId,userId);
    }

    @Override
    public PostLikes findPostLikesByPostIdUserId(int postId, String userId) {
        PostLikes postLikes= new PostLikes();
        postLikes.setUserId(userId);
        postLikes.setPostId(postId);
        return postLikesRepository.find(userId,postId);
    }

    @Override
    public List<VideoRoom> videoList() {
        List<VideoRoom> videos=communityRepositorySupport.findVideos();
        return videos;
    }

    @Override
    public VideoRoom createVideo(VideoRoom videoInfo) {
        VideoRoom video= videoRepository.save(videoInfo);
        return video;
    }


}

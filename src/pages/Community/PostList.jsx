import React from "react";
import { Link } from "react-router-dom";

const dummyPosts = [
  {
    id: 1,
    title: "Updated odds to win the 2025 NBA Finals, per DKSportsbook",
    author: "유저1",
  },
  {
    id: 1,
    title:
      "Celtics forward Jaylen Brown wasn’t just playing through pain during the playoffs",
    author: "유저2",
  },
];

const PostList = () => {
  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">게시판</h2>
      <ul>
        {dummyPosts.map((post) => (
          <li key={post.id} className="mb-2">
            <Link
              to={`/Community/${post.id}`}
              className="text-blue-600 hover:underline"
            >
              {post.title} - {post.author}
            </Link>
          </li>
        ))}
      </ul>
      <Link
        to="/Community/new"
        className="inline-block mt-4 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        게시물 작성
      </Link>
    </div>
  );
};

export default PostList;

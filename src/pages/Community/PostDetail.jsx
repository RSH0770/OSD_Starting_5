import React, { useState } from "react";
import { useParams } from "react-router-dom";

const PostDetail = () => {
  const { id } = useParams();
  const [comments, setComments] = useState([]);
  const [input, setInput] = useState("");

  const handleAddComment = () => {
    if (!input) return;
    setComments([...comments, input]);
    setInput("");
  };

  return (
    <div className="p-4">
      <h2 className="text-2xl font-bold">게시물 #{id}</h2>
      <p className="mt-2 text-gray-700">게시물 내용이 여기에 들어갑니다.</p>

      <hr className="my-4" />

      <h3 className="text-lg font-semibold"></h3>
      <ul clsssName="list-disc pl-5 mb-3">
        {comments.map((c, index) => (
          <li key={index}>{c}</li>
        ))}
      </ul>
      <div className="flex gap-2">
        <input
          value={input}
          onChange={(e) => setInput(e.target.value)}
          className="border rounded px-2 py-1 flex-1"
          placeholder="댓글을 입력하세요"
        />
        <button
          onClick={handleAddComment}
          className="bg-blue-500 text-white px-4 rounded"
        >
          등록
        </button>
      </div>
    </div>
  );
};

export default PostDetail;

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();

    if (email === "user@example.com" && password === "1234") {
      alert("로그인 성공!");
    } else {
      setError("이메일 또는 비밀번호가 올바르지 않습니다.");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen bg-netural-200">
      <div className="bg-gradient-to-l from-blue-600 to-red-500 p-8 rounded-2xl shadow-xl w-96">
        <div className="bg-neutral-300 p-5 rounded-2xl">
          <h2 className="text-3xl font-bold mb-6 text-center text-black">
            로그인
          </h2>

          <form onSubmit={handleLogin} className="flex flex-col gap-4">
            <input
              type="email"
              placeholder="이메일"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="px-4 py-2 border rounded-xl outline-none focus:ring-2 focus:ring-blue-400"
            />
            <input
              type="password"
              placeholder="비밀번호"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="px-4 py-2 border rounded-xl outline-none focus:ring-2 focus:ring-blue-400"
            />

            {error && <p className="text-red-500 text-sm">{error}</p>}

            <button
              type="submit"
              className="bg-neutral-900 hover:bg-neutral-700 text-white py-2 rounded-xl font-semibold transition"
            >
              로그인
            </button>
          </form>

          <div className="mt-4 text-sm text-center text-gray-600">
            계정이 없으신가요?{" "}
            <button
              onClick={() => navigate("/SignUp")}
              className="text-blue-600 hover:underline"
            >
              회원가입
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;

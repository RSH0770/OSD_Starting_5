import React from "react";
import Login from "./pages/Login";
import Stats from "./pages/Stats";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const homeIconOut =
  "https://img.icons8.com/?size=100&id=i6fZC6wuprSu&format=png&color=FFFFFF";

const homeIconIn =
  "https://img.icons8.com/?size=100&id=1iF9PyJ2Thzo&format=png&color=FFFFFF";

const backIconOut =
  "https://img.icons8.com/?size=100&id=Ekcv7HUsxLz9&format=png&color=FFFFFF";

const backIconIn =
  "https://img.icons8.com/?size=100&id=yiR4rPf7BGje&format=png&color=FFFFFF";

const searchIconOut =
  "https://img.icons8.com/?size=100&id=132&format=png&color=000000";

const searchIconIn =
  "https://img.icons8.com/?size=100&id=7695&format=png&color=000000";

const NavBar = () => {
  const navigate = useNavigate();
  const [searchIconHover, setSearchIconHover] = useState(false);
  const [homeIconHover, setHomeIconHover] = useState(false);
  const [backIconHover, setBackIconHover] = useState(false);

  const home = () => navigate("/");
  const login = () => navigate("Login");
  const introduce = () => navigate("Introduce");
  const community = () => navigate("Community");
  const stats = () => navigate("Stats");
  const backButton = () => navigate(-1);

  return (
    <div>
      <div className="flex flex-row items-center justify-around bg-neutral-300">
        <div className="flex justify-start">
          <img
            src="src\assets\Starting_5_Logo.png"
            className="w-30 h-17 ml-5"
            onClick={home}
          ></img>
        </div>
        {/* <img
          src={backIconHover ? backIconIn : backIconOut}
          onMouseEnter={() => setBackIconHover(true)}
          onMouseLeave={() => setBackIconHover(false)}
          onClick={backButton}
          className="w-10 h-10 p-2"
          alt="back icon"
        ></img>
        <img
          src={homeIconHover ? homeIconIn : homeIconOut}
          onMouseEnter={() => setHomeIconHover(true)}
          onMouseLeave={() => setHomeIconHover(false)}
          onClick={home}
          className="w-10 h-10 p-2"
          alt="home icon"
        ></img> */}
        <div onClick={introduce} className="p-2 text-white hover:font-bold">
          Introduce
        </div>
        <div onClick={login} className="p-2 text-white hover:font-bold">
          Login
        </div>
        <div onClick={community} className="p-2 text-white hover:font-bold">
          Community
        </div>
        <div onClick={stats} className="p-2 text-white hover:font-bold">
          Stats
        </div>

        <div className="flex items-center mr-5">
          <input
            placeholder="검색어를 입력하세요."
            className="w-50 h-8 m-1 px-3 py-2 border text-sm rounded-xl bg-neutral-100"
          ></input>
          <button>
            <img
              src={searchIconHover ? searchIconIn : searchIconOut}
              onMouseEnter={() => setSearchIconHover(true)}
              onMouseLeave={() => setSearchIconHover(false)}
              alt="search icon"
              className="w-6 h-6"
            ></img>
          </button>
        </div>
      </div>
    </div>
  );
};

export default NavBar;

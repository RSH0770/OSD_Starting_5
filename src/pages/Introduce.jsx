import React from "react";

const teams = [
  { name: "Atlanta Hawks", id: "1610612737" },
  { name: "Boston Celtics", id: "1610612738" },
  { name: "Brooklyn Nets", id: "1610612751" },
  { name: "Charlotte Hornets", id: "1610612766" },
  { name: "Chicago Bulls", id: "1610612741" },
  { name: "Cleveland Cavaliers", id: "1610612739" },
  { name: "Dallas Mavericks", id: "1610612742" },
  { name: "Denver Nuggets", id: "1610612743" },
  { name: "Detroit Pistons", id: "1610612765" },
  { name: "Golden State Warriors", id: "1610612744" },
  { name: "Houston Rockets", id: "1610612745" },
  { name: "Indiana Pacers", id: "1610612754" },
  { name: "Los Angeles Clippers", id: "1610612746" },
  { name: "Los Angeles Lakers", id: "1610612747" },
  { name: "Memphis Grizzlies", id: "1610612763" },
  { name: "Miami Heat", id: "1610612748" },
  { name: "Milwaukee Bucks", id: "1610612749" },
  { name: "Minnesota Timberwolves", id: "1610612750" },
  { name: "New Orleans Pelicans", id: "1610612740" },
  { name: "New York Knicks", id: "1610612752" },
  { name: "Oklahoma City Thunder", id: "1610612760" },
  { name: "Orlando Magic", id: "1610612753" },
  { name: "Philadelphia 76ers", id: "1610612755" },
  { name: "Phoenix Suns", id: "1610612756" },
  { name: "Portland Trail Blazers", id: "1610612757" },
  { name: "Sacramento Kings", id: "1610612758" },
  { name: "San Antonio Spurs", id: "1610612759" },
  { name: "Toronto Raptors", id: "1610612761" },
  { name: "Utah Jazz", id: "1610612762" },
  { name: "Washington Wizards", id: "1610612764" },
];

const Introduce = () => {
  return (
    <div className="h-auto min-h-screen p-10 mx-auto bg-gradient-to-l from-blue-600 to-red-500">
      <div className="bg-neutral-200 p-5 rounded-2xl">
        <h1 className="text-4xl font-bold mb-8 text-center text-orange-600">
          🏀 농구?
        </h1>

        <section className="mb-8">
          <p className="text-lg leading-relaxed">
            <li>
              농구는 5명이 손을 사용해 공을 링(림)에 던져 넣어 득점하는 것
            </li>
            <li>
              승부를 겨루어, 제한된 시간 안에 더 높은 점수를 달성한 쪽이
              승리하는 단체 구기 종목 입니다.
            </li>
            <li>
              빠른 속도와 역동적인 움직임, 팀워크가 중요한 경기로 많은 사람들의
              사랑을 받고 있습니다.
            </li>
          </p>
        </section>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div className="bg-neutral-100 shadow-md rounded-2xl p-6">
            <h2 className="text-2xl font-semibold text-blue-600 mb-3">
              🏀 경기 규칙
            </h2>
            <ul className="list-disc list-inside text-gray-700 space-y-2">
              <li>경기 시간은 4쿼터, 각 10분(또는 12분)으로 진행됩니다.</li>
              <li>공격 시 24초 안에 슛을 해야 합니다.</li>
              <li>상대 팀 바스켓에 공을 넣으면 2점 또는 3점 득점합니다.</li>
              <li>파울을 범하면 자유투를 허용합니다.</li>
            </ul>
          </div>

          <div className="bg-neutral-100 shadow-md rounded-2xl p-6">
            <h2 className="text-2xl font-semibold text-red-600 mb-3">
              ⛹️‍♂️ 농구의 매력
            </h2>
            <ul className="list-disc list-inside text-gray-700 space-y-2">
              <li>빠른 전환과 전략적인 플레이</li>
              <li>한순간에 승부가 뒤집힐 수 있는 역동성</li>
              <li>팀워크와 개인기의 완벽한 조화</li>
              <li>전 세계적으로 인기를 끌고 있는 스포츠</li>
            </ul>
          </div>

          <div className="bg-neutral-100 mt-5 p-6 rounded-2xl shadow-md">
            <h2 className="text-2xl font-bold text-blue-700 mb-4">
              📚 농구의 역사
            </h2>
            <p className="text-gray-800 mb-6 leading-relaxed">
              <li>
                농구는 캐나다계 미국인 <strong>제임스 네이스미스</strong>가
                고안한 스포츠
              </li>
              <li>
                양팀에 각 5명의 선수가 한 개의 공을 상대팀 3m 높이의 바스켓에
                공을 던져 넣는 경기입니다.
              </li>
              <li>
                공과 바구니 역할의 바스켓만 있으면 두 명만으로도 작은 공간에서
                얼마든지 경기할 수 있다는 장점 덕분에, 농구는 현재 전 세계에서
                가장 인기 있는 스포츠 중 하나가 되었습니다.
              </li>
            </p>
          </div>

          <div className="bg-neutral-100 mt-5 p-6 rounded-2xl shadow-md">
            <h2 className="text-2xl font-bold text-red-600 mb-4">
              🏀 농구의 세 가지 핵심 기본기
            </h2>
            <ul className="list-disc list-inside text-gray-700 space-y-2 text-base">
              <li>
                <strong>패스(Pass)</strong>로 팀원 간의 원활한 공 전달을 통해
                공격 기회를 만듭니다.
              </li>
              <li>
                <strong>드리블(Dribble)</strong>은 공을 바닥에 튀기며 이동하는
                기술로, 상대를 제치고 공간을 확보하는 데 필수적입니다.
              </li>
              <li>
                <strong>슛(Shoot)</strong>은 바스켓을 향해 공을 던져 득점을
                노리는 동작으로, 경기의 핵심이 되는 기술입니다.
              </li>
            </ul>
          </div>
        </div>

        <section className="mt-10 bg-orange-100 p-6 rounded-xl">
          <h2 className="text-xl font-semibold text-orange-700 mb-3 text-center">
            🏀 NBA 리그 소개
          </h2>
          <p className="text-gray-800 mb-4">
            <li>
              NBA(National Basketball Association)는 세계 최고의 프로 농구
              리그로, 미국과 캐나다를 기반으로 30개의 팀이 참가하고 있습니다.
            </li>
            <li>
              각 팀은 <strong>정규 시즌(24-25 시즌)</strong>과{" "}
              <strong>플레이오프</strong>를 통해 챔피언을 가립니다.
            </li>
            <li>
              {" "}
              수준 높은 경기력과 <strong>르브론 제임스</strong>,{" "}
              <strong>스테판 커리</strong>, <strong>케빈 듀란트</strong> 등
              화려한 스타 선수들로 전 세계 팬들에게 사랑받고 있습니다.
            </li>
          </p>

          <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mt-6">
            {teams.map((team) => (
              <div
                key={team.id}
                className="flex items-center gap-3 p-2 bg-white shadow rounded-xl"
              >
                <img
                  src={`https://cdn.nba.com/logos/nba/${team.id}/primary/L/logo.svg`}
                  alt={`${team.name} logo`}
                  className="w-10 h-10"
                />
                <span className="font-medium text-gray-800">{team.name}</span>
              </div>
            ))}
          </div>
        </section>
      </div>
    </div>
  );
};

export default Introduce;

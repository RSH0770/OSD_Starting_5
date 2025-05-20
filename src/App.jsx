import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import Introduce from "./pages/Introduce";
import PostList from "./pages/Community/PostList";
import PostForm from "./pages/Community/PostForm";
import PostDetail from "./pages/Community/PostDetail";
import Stats from "./pages/Stats";
import NotFound from "./pages/NotFound";
import Layout from "./Layout";
import CommunityLayout from "./pages/Community/CommunityLayout";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="Login" element={<Login />} />
          <Route path="Introduce" element={<Introduce />} />
          <Route path="Community" element={<CommunityLayout />}>
            <Route index element={<PostList />} />
            <Route path="new" element={<PostForm />} />
            <Route path=":id" element={<PostDetail />} />
          </Route>
          <Route path="Stats" element={<Stats />}></Route>
          <Route path="SignUp" element={<SignUp />} />
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;

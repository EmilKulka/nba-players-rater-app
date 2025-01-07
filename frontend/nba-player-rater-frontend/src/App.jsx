import { Routes, Route } from 'react-router-dom'
import MatchupPage from './pages/MatchupPage/MatchupPage.jsx'
import LeaderboardPage from './pages/LeaderboardPage/LeaderboardPage.jsx'
import Navbar from './components/Navbar/Navbar'

function App() {
  return (
    <>
      <Navbar/>
      <Routes>
        <Route path="/" element={<MatchupPage/>}></Route>
        <Route path="/leaderboard" element={<LeaderboardPage/>}></Route>
      </Routes>
    </>
  )
}

export default App

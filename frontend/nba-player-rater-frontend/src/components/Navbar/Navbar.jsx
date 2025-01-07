import { Link } from 'react-router-dom'
import './style.css'

export default function Navbar() {
    return (
        <nav className="navbar">
            <div className="nav-container">
                <Link to="/" className="nav-item">Matchup</Link>
                <Link to="/leaderboard" className="nav-item">Leaderboard</Link>
            </div>
        </nav>
    )
}
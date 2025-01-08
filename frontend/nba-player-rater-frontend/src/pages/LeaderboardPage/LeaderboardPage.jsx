// pages/LeaderboardPage/LeaderboardPage.jsx
import { useState, useEffect } from 'react';
import { leaderboardService } from '../../services/api/leaderboardService';
import './style.css';

export default function LeaderboardPage() {
    const [players, setPlayers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        loadLeaderboard();
    }, []);

    const loadLeaderboard = async () => {
        try {
            setLoading(true);
            const data = await leaderboardService.getLeaderboard();
            setPlayers(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    if (loading) return <div className="loading">Loading...</div>;
    if (error) return <div className="error">{error}</div>;

    return (
        <div className="leaderboard-container">
            <h1 className="leaderboard-title">Top Players</h1>
            <div className="leaderboard-table">
                <table>
                    <thead>
                        <tr>
                            <th>Rank</th>
                            <th>Player</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        {players.map((player, index) => (
                            <tr key={player.id} className="player-row">
                                <td className="rank-cell">{index + 1}</td>
                                <td className="player-cell">
                                    <img 
                                        src={player.imgUrl} 
                                        alt={`${player.name} ${player.surname}`} 
                                        className="player-thumbnail"
                                    />
                                    <span className="player-name">
                                        {player.name} {player.surname}
                                    </span>
                                </td>
                                <td className="score-cell">{player.score}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
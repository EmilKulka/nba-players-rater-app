import { useState, useEffect } from 'react';
import PlayerCard from "../PlayerCard/PlayerCard";
import { matchupService } from '../../services/api/matchupService';
import './style.css';

export default function Matchup() {
    const [matchup, setMatchup] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [voting, setVoting] = useState(false);

    useEffect(() => {
        loadMatchup();
    }, []);

    const loadMatchup = async () => {
        try {
            setLoading(true);
            const data = await matchupService.getCurrentMatchup();
            setMatchup(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    const handleVote = async (playerId) => {
        try {
            setVoting(true);
            await matchupService.submitVote(matchup.id, playerId);
            await loadMatchup(); 
        } catch (err) {
            setError(err.message);
        } finally {
            setVoting(false);
        }
    };


    if (loading) return <div className="loading">Loading...</div>;
    if (error) return <div className="error">{error}</div>;
    if (!matchup) return <div>No matchup available</div>;

    return (
        <div className="matchup-container">
            <PlayerCard 
                id={matchup.playerId}
                name={matchup.playerName}
                surname={matchup.playerSurname}
                imgUrl={matchup.playerImgUrl}
                onVote={() => handleVote(matchup.playerId)}
                disabled={voting}
            />
            <PlayerCard 
                id={matchup.player2Id}
                name={matchup.player2Name}
                surname={matchup.player2Surname}
                imgUrl={matchup.player2ImgUrl}
                onVote={() => handleVote(matchup.player2Id)}
                disabled={voting}
            />
        </div>
    );
}
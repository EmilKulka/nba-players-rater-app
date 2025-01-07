import PlayerCard from "../PlayerCard/PlayerCard"
import './style.css' 


export default function Matchup() {
    const handlePlayerClick = (playerId) => {
        console.log(`Clicked player: ${playerId}`);  
    };

    const handleVote = (playerId) => {
        console.log(`Voted for player: ${playerId}`);
    };

    return (
        <div className="matchup-container">
            <PlayerCard 
                id="1" 
                name="John" 
                surname="Doe" 
                imgUrl="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1627824.png"
                onClick={() => handlePlayerClick("1")}
                onVote={handleVote}
            />
            <PlayerCard 
                id="2" 
                name="Jane" 
                surname="Smith" 
                imgUrl="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1630202.png"
                onClick={() => handlePlayerClick("2")}
                onVote={handleVote}
            />
        </div>
    )
}
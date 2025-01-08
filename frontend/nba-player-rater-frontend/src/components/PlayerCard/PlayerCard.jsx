import './style.css'

export default function PlayerCard({ id, name, surname, imgUrl, onVote, disabled }) {
    return (
        <div className="player-card">
            <img 
                src={imgUrl} 
                alt={`${name} ${surname}`} 
                className="player-img"
            />
            <div className="player-info">
                <div className="player-name">{name} {surname}</div>
                <button 
                    className="vote-button"
                    onClick={onVote}
                    disabled={disabled}
                >
                    {disabled ? 'Voting...' : 'Vote'}
                </button>
            </div>
        </div>
    );
}
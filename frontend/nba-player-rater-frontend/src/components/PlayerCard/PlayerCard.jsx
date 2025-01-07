import './style.css'

export default function PlayerCard({ id, name, surname, imgUrl, onVote, onClick }) {
    return (
        <div className="player-card" onClick={onClick}>
            <img 
                src={imgUrl} 
                alt={`${name} ${surname}`} 
                className="player-img"
            />
            <div className="player-info">
                <div className="player-name">{name} {surname}</div>
                <button 
                    className="vote-button"
                    onClick={(e) => {
                        e.stopPropagation(); 
                        onVote?.(id);
                    }}
                >
                    Vote
                </button>
            </div>
        </div>
    )
}
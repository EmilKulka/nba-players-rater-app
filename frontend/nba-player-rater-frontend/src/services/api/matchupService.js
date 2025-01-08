import api from '../config';

export const matchupService = {
    getCurrentMatchup: async () => {
        try {
            const { data } = await api.get('/matchup');
            return data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Failed to fetch matchup');
        }
    },

    submitVote: async (matchupId, winnerId) => {
        try {
            const { data } = await api.post('/matchup/answer', {
                matchupId,
                winnerId
            });
            return data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Failed to submit vote');
        }
    }
};
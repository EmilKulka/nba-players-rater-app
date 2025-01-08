// services/api/leaderboardService.js
import api from '../config';

export const leaderboardService = {
    getLeaderboard: async () => {
        try {
            const { data } = await api.get('/leaderboard');
            return data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Failed to fetch leaderboard');
        }
    }
};
const authReducer = (state = null, action) => {
  switch (action.type) {
    case 'authUsers/merge': {
      const authUsersMap = action.payload;
      return Object.values(authUsersMap)[0];
    }
    case 'authUsers/destroy': {
      return null;
    }
    default: {
      return state;
    }
  }
};

export default authReducer;

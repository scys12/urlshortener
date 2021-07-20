import { useSelector } from 'react-redux';

function useAuth() {
  const auth = useSelector((state) => state.auth);
  return auth;
}
export default useAuth;

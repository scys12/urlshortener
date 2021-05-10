kubectl apply -f https://bit.ly/kong-ingress-dbless
kubectl apply -f plugin-configmaps.yaml
kubectl patch deployment ingress-kong -p "$(cat plugin-depl.yaml)" -n kong
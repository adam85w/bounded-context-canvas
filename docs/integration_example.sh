service='http://localhost:8083/api/bounded-context-canvas/html?templateName=modern'
input='./integration_example.json'
output_directory='./'

uuid=$(uuidgen)
curl -X 'POST' \
  ${service} \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -T ${input} \
  -o "${output_directory}"'bounded-context-canvas-'"${uuid}"'.html'
xdg-open "${output_directory}"'bounded-context-canvas-'"${uuid}"'.html'
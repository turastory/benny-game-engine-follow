#version 330

in vec2 texturePos0;
in vec3 normal0;
in vec3 worldPos0;

out vec4 fragColor;

struct BaseLight
{
  vec3 color;
  float intensity;
};

struct DirectionalLight
{
  BaseLight base;
  vec3 direction;
};

uniform vec3 eyePos;

uniform vec3 baseColor;
uniform vec3 ambientLight;
uniform sampler2D sampler;

uniform float specularIntensity;
uniform float specularPower;

uniform DirectionalLight directionalLight;

vec4 calculateLight(BaseLight base, vec3 direction, vec3 normal)
{
  float diffuseFactor = dot(normal, -direction);

  vec4 diffuseColor = vec4(0, 0, 0, 0);
  vec4 specularColor = vec4(0, 0, 0, 0);

  if (diffuseFactor > 0)
  {
    diffuseColor = vec4(base.color, 1.0) * base.intensity * diffuseFactor;

    vec3 directionToEye = normalize(eyePos - worldPos0);
    vec3 reflectDirection = normalize(reflect(direction, normal));

    float specularFactor = dot(directionToEye, reflectDirection);
    specularFactor = pow(specularFactor, specularPower);

    if (specularFactor > 0)
    {
      specularColor = vec4(base.color, 1.0) * specularIntensity *  specularFactor;
    }
  }

  return diffuseColor + specularColor;
}

vec4 calculateDirectionalLight(DirectionalLight dl, vec3 normal)
{
  return calculateLight(dl.base, -dl.direction, normal);
}

void main()
{
  vec4 textureColor = texture2D(sampler, texturePos0.xy);
  vec4 totalLight = vec4(ambientLight, 1);
  vec4 color = vec4(baseColor, 1);

  if (textureColor != vec4(0, 0, 0, 0))
    color *= textureColor;

  vec3 normal = normalize(normal0);

  totalLight += calculateDirectionalLight(directionalLight, normal);

  fragColor = color * totalLight;
}
